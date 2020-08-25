package com.pet.springdata.service.trivia.impl;

import com.pet.springdata.model.trivia.OpenTriviaDatabaseResponse;
import com.pet.springdata.model.trivia.OpenTriviaDatabaseToken;
import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.Trivia;
import com.pet.springdata.repository.trivia.TriviaRepository;
import com.pet.springdata.service.trivia.OpenTriviaDatabaseService;
import com.pet.springdata.util.trivia.TriviaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.pet.springdata.util.Constants.EMPTY_STRING;
import static com.pet.springdata.util.Constants.OPEN_TRIVIA_DB_API_URL;
import static com.pet.springdata.util.Constants.OPEN_TRIVIA_DB_TOKEN_URL;

@Service
public class TriviaService implements OpenTriviaDatabaseService {

    private static final Logger LOG = LoggerFactory.getLogger(TriviaService.class);
    public static final int MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST = 50;

    private final TriviaRepository triviaRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public TriviaService(TriviaRepository triviaRepository, RestTemplate restTemplate) {
        this.triviaRepository = triviaRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<TriviaDTO> getTrivia(int numberOfTrivia) {
        LOG.info("Getting {} Trivia.", numberOfTrivia);

        return TriviaUtil.unescapeHtmlTagsOfAllTrivia(
                getResultsFromOpenTriviaDatabaseResponse(numberOfTrivia, getTokenFromOpenTriviaDatabase())
        );
    }

    private String getTokenFromOpenTriviaDatabase() {
        Optional<OpenTriviaDatabaseToken> optionalOpenTriviaDatabaseToken = Optional.ofNullable(
                restTemplate.getForObject(OPEN_TRIVIA_DB_TOKEN_URL, OpenTriviaDatabaseToken.class)
        );

        return optionalOpenTriviaDatabaseToken
                .map(OpenTriviaDatabaseToken::getToken)
                .orElse(EMPTY_STRING);
    }

    private List<TriviaDTO> getResultsFromOpenTriviaDatabaseResponse(int numberOfTrivia, String token) {
        Optional<OpenTriviaDatabaseResponse> optionalOpenTriviaDatabaseResponse = Optional.ofNullable(
                restTemplate.getForObject(String.format(OPEN_TRIVIA_DB_API_URL, numberOfTrivia, token), OpenTriviaDatabaseResponse.class)
        );

        return optionalOpenTriviaDatabaseResponse
                .map(OpenTriviaDatabaseResponse::getResults)
                .orElse(Collections.emptyList());
    }

    @Override
    public ResponseEntity<List<Trivia>> saveTrivia(int numberOfTrivia) {
        LOG.info("Saving {} Trivia.", numberOfTrivia);
        List<TriviaDTO> triviaDTOList = TriviaUtil.unescapeHtmlTagsOfAllTrivia(
                getResultsDependingOnTheNumberOfMaximumRequests(numberOfTrivia, getTokenFromOpenTriviaDatabase())
        );
        List<Trivia> triviaList = triviaRepository.saveAll(TriviaUtil.transformTriviaDTOListToTriviaList(triviaDTOList));

        return ResponseEntity.ok(triviaList);
    }

    private List<TriviaDTO> getResultsDependingOnTheNumberOfMaximumRequests(int numberOfTrivia, String token) {
        List<TriviaDTO> triviaDTOList;

        double numberOfMaximumRequests = Double.parseDouble(String.valueOf(numberOfTrivia)) / MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST;
        if (numberOfMaximumRequests <= 1) {
            triviaDTOList = getResultsFromOpenTriviaDatabaseResponse(numberOfTrivia, token);
        } else {
            triviaDTOList = getResultsForMoreThanOneMaximumRequest(numberOfMaximumRequests, token, numberOfTrivia);
        }

        return triviaDTOList;
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
