package com.pet.springdata.domain.trivia.impl;

import com.pet.springdata.domain.trivia.IOpenTriviaDatabaseService;
import com.pet.springdata.domain.trivia.model.OpenTriviaDatabaseResponse;
import com.pet.springdata.domain.trivia.model.OpenTriviaDatabaseResult;
import com.pet.springdata.domain.trivia.model.OpenTriviaDatabaseToken;
import com.pet.springdata.domain.trivia.util.OpenTriviaDatabaseUtil;
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

import static com.pet.springdata.domain.util.Constants.EMPTY_STRING;
import static com.pet.springdata.domain.util.Constants.MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST;
import static com.pet.springdata.domain.util.Constants.URL_OPEN_TRIVIA_DB_API;
import static com.pet.springdata.domain.util.Constants.URL_OPEN_TRIVIA_DB_TOKEN;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenTriviaDatabaseService implements IOpenTriviaDatabaseService {

    @NonNull
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<List<OpenTriviaDatabaseResult>> getTrivia(int numberOfTrivia) {
        log.info("Getting {} Trivia.", numberOfTrivia);
        List<OpenTriviaDatabaseResult> resultsFromOpenTriviaDatabase = getResultsDependingOnTheNumberOfMaximumRequests(numberOfTrivia, getTokenFromOpenTriviaDatabase());

        return ResponseEntity.ok(OpenTriviaDatabaseUtil.unescapeHtmlTagsOfAllTrivia(resultsFromOpenTriviaDatabase));
    }

    public String getTokenFromOpenTriviaDatabase() {
        Optional<OpenTriviaDatabaseToken> optionalOpenTriviaDatabaseToken = Optional.ofNullable(
                restTemplate.getForObject(URL_OPEN_TRIVIA_DB_TOKEN, OpenTriviaDatabaseToken.class)
        );

        return optionalOpenTriviaDatabaseToken
                .map(OpenTriviaDatabaseToken::getToken)
                .orElse(EMPTY_STRING);
    }

    public List<OpenTriviaDatabaseResult> getResultsDependingOnTheNumberOfMaximumRequests(int numberOfTrivia, String token) {
        List<OpenTriviaDatabaseResult> openTriviaDatabaseResultList;

        double numberOfMaximumRequests = Double.parseDouble(String.valueOf(numberOfTrivia)) / MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST;
        if (numberOfMaximumRequests <= 1) {
            openTriviaDatabaseResultList = getResultsFromOpenTriviaDatabaseResponse(numberOfTrivia, token);
        } else {
            openTriviaDatabaseResultList = getResultsForMoreThanOneMaximumRequest(numberOfMaximumRequests, token, numberOfTrivia);
        }

        return openTriviaDatabaseResultList;
    }

    public List<OpenTriviaDatabaseResult> getResultsFromOpenTriviaDatabaseResponse(int numberOfTrivia, String token) {
        Optional<OpenTriviaDatabaseResponse> optionalOpenTriviaDatabaseResponse = Optional.ofNullable(
                restTemplate.getForObject(String.format(URL_OPEN_TRIVIA_DB_API, numberOfTrivia, token), OpenTriviaDatabaseResponse.class)
        );

        return optionalOpenTriviaDatabaseResponse
                .map(OpenTriviaDatabaseResponse::getResults)
                .orElse(Collections.emptyList());
    }

    private List<OpenTriviaDatabaseResult> getResultsForMoreThanOneMaximumRequest(double numberOfMaximumRequests, String token, int numberOfTrivia) {
        List<OpenTriviaDatabaseResult> openTriviaDatabaseResultList = new ArrayList<>();

        int integerPartOfNumberOfMaximumRequests = (int) numberOfMaximumRequests;
        for (int i = 1; i <= integerPartOfNumberOfMaximumRequests; i++) {
            openTriviaDatabaseResultList.addAll(getResultsFromOpenTriviaDatabaseResponse(MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST, token));
        }

        int numberOfRemainders = numberOfTrivia % MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST;
        openTriviaDatabaseResultList.addAll(getResultsFromOpenTriviaDatabaseResponse(numberOfRemainders, token));

        return openTriviaDatabaseResultList;
    }
}
