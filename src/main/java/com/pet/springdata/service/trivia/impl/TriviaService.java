package com.pet.springdata.service.trivia.impl;

import com.pet.springdata.model.trivia.OpenTriviaDatabaseResponse;
import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.service.trivia.OpenTriviaDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TriviaService implements OpenTriviaDatabaseService {

    private static final Logger LOG = LoggerFactory.getLogger(TriviaService.class);

    private static final String OPEN_TRIVIA_DB_API_URL = "https://opentdb.com/api.php?amount=%s";

    private static final int NUMBER_OF_TRIVIA = 10;

    private final RestTemplate restTemplate;

    @Autowired
    public TriviaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<TriviaDTO> getTrivia() {
        LOG.info("Getting all Trivia.");
        OpenTriviaDatabaseResponse openTriviaDatabaseResponse = restTemplate.getForObject(String.format(OPEN_TRIVIA_DB_API_URL, NUMBER_OF_TRIVIA), OpenTriviaDatabaseResponse.class);

        return unescapeHtmlTagsOfAllTrivia(openTriviaDatabaseResponse.getResults());
    }

    private List<TriviaDTO> unescapeHtmlTagsOfAllTrivia(List<TriviaDTO> results) {
        return results.stream().map(this::unescapeHtmlTagsOfTrivia).collect(Collectors.toList());
    }

    private TriviaDTO unescapeHtmlTagsOfTrivia(TriviaDTO result) {
        result.setCategory(HtmlUtils.htmlUnescape(result.getCategory()));
        result.setType(HtmlUtils.htmlUnescape(result.getType()));
        result.setDifficulty(HtmlUtils.htmlUnescape(result.getDifficulty()));
        result.setQuestion(HtmlUtils.htmlUnescape(result.getQuestion()));
        result.setCorrectAnswer(HtmlUtils.htmlUnescape(result.getCorrectAnswer()));
        result.setIncorrectAnswers(result.getIncorrectAnswers().stream().map(HtmlUtils::htmlUnescape).collect(Collectors.toList()));

        return result;
    }
}
