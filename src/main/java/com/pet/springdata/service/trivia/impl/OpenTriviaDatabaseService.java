package com.pet.springdata.service.trivia.impl;

import com.pet.springdata.model.trivia.OpenTriviaDatabaseResponse;
import com.pet.springdata.model.trivia.OpenTriviaDatabaseToken;
import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.service.trivia.IOpenTriviaDatabaseService;
import com.pet.springdata.util.trivia.TriviaUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.pet.springdata.util.Constants.EMPTY_STRING;
import static com.pet.springdata.util.Constants.MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST;
import static com.pet.springdata.util.Constants.URL_OPEN_TRIVIA_DB_API;
import static com.pet.springdata.util.Constants.URL_OPEN_TRIVIA_DB_TOKEN;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenTriviaDatabaseService implements IOpenTriviaDatabaseService {

    @NonNull
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<List<TriviaDTO>> getTrivia(int numberOfTrivia) {
        log.info("Getting {} Trivia.", numberOfTrivia);
        List<TriviaDTO> resultsFromOpenTriviaDatabase = getResultsDependingOnTheNumberOfMaximumRequests(numberOfTrivia, getTokenFromOpenTriviaDatabase());

        return ResponseEntity.ok(TriviaUtil.unescapeHtmlTagsOfAllTrivia(resultsFromOpenTriviaDatabase));
    }

    public String getTokenFromOpenTriviaDatabase() {
        Optional<OpenTriviaDatabaseToken> optionalOpenTriviaDatabaseToken = Optional.ofNullable(
                restTemplate.getForObject(URL_OPEN_TRIVIA_DB_TOKEN, OpenTriviaDatabaseToken.class)
        );

        return optionalOpenTriviaDatabaseToken
                .map(OpenTriviaDatabaseToken::getToken)
                .orElse(EMPTY_STRING);
    }

    public List<TriviaDTO> getResultsDependingOnTheNumberOfMaximumRequests(int numberOfTrivia, String token) {
        List<TriviaDTO> triviaDTOList;

        double numberOfMaximumRequests = Double.parseDouble(String.valueOf(numberOfTrivia)) / MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST;
        if (numberOfMaximumRequests <= 1) {
            triviaDTOList = getResultsFromOpenTriviaDatabaseResponse(numberOfTrivia, token);
        } else {
            triviaDTOList = getResultsForMoreThanOneMaximumRequest(numberOfMaximumRequests, token, numberOfTrivia);
        }

        return triviaDTOList;
    }

    public List<TriviaDTO> getResultsFromOpenTriviaDatabaseResponse(int numberOfTrivia, String token) {
        Optional<OpenTriviaDatabaseResponse> optionalOpenTriviaDatabaseResponse = Optional.ofNullable(
                restTemplate.getForObject(String.format(URL_OPEN_TRIVIA_DB_API, numberOfTrivia, token), OpenTriviaDatabaseResponse.class)
        );

        return optionalOpenTriviaDatabaseResponse
                .map(OpenTriviaDatabaseResponse::getResults)
                .orElse(Collections.emptyList());
    }

    private List<TriviaDTO> getResultsForMoreThanOneMaximumRequest(double numberOfMaximumRequests, String token, int numberOfTrivia) {
        List<TriviaDTO> triviaDTOList = new ArrayList<>();

        int integerPartOfNumberOfMaximumRequests = (int) numberOfMaximumRequests;
        for (int i = 1; i <= integerPartOfNumberOfMaximumRequests; i++) {
            triviaDTOList.addAll(getResultsFromOpenTriviaDatabaseResponse(MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST, token));
        }

        int numberOfRemainders = numberOfTrivia % MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST;
        triviaDTOList.addAll(getResultsFromOpenTriviaDatabaseResponse(numberOfRemainders, token));

        return triviaDTOList;
    }
}
