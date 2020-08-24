package com.pet.springdata.service.trivia.impl;

import com.pet.springdata.model.trivia.OpenTriviaDatabaseResponse;
import com.pet.springdata.model.trivia.OpenTriviaDatabaseToken;
import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.service.trivia.OpenTriviaDatabaseService;
import com.pet.springdata.util.trivia.TriviaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.pet.springdata.util.Constants.EMPTY_STRING;
import static com.pet.springdata.util.Constants.OPEN_TRIVIA_DB_API_URL;
import static com.pet.springdata.util.Constants.OPEN_TRIVIA_DB_TOKEN_URL;

@Service
public class TriviaService implements OpenTriviaDatabaseService {

    private static final Logger LOG = LoggerFactory.getLogger(TriviaService.class);

    private final RestTemplate restTemplate;

    @Autowired
    public TriviaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<TriviaDTO> getTrivia(Integer numberOfTrivia) {
        LOG.info("Getting {} Trivia.", numberOfTrivia);
        Optional<OpenTriviaDatabaseToken> optionalOpenTriviaDatabaseToken = Optional.ofNullable(
                restTemplate.getForObject(OPEN_TRIVIA_DB_TOKEN_URL, OpenTriviaDatabaseToken.class)
        );

        String token = optionalOpenTriviaDatabaseToken
                .map(OpenTriviaDatabaseToken::getToken)
                .orElse(EMPTY_STRING);

        Optional<OpenTriviaDatabaseResponse> optionalOpenTriviaDatabaseResponse = Optional.ofNullable(
                restTemplate.getForObject(String.format(OPEN_TRIVIA_DB_API_URL, numberOfTrivia, token), OpenTriviaDatabaseResponse.class)
        );

        return TriviaUtil.unescapeHtmlTagsOfAllTrivia(
                optionalOpenTriviaDatabaseResponse
                        .map(OpenTriviaDatabaseResponse::getResults)
                        .orElse(Collections.emptyList())
        );
    }
}
