package com.pet.springdata.controller.rest.trivia;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.service.trivia.OpenTriviaDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TriviaRestController {

    private static final Logger LOG = LoggerFactory.getLogger(TriviaRestController.class);

    private final OpenTriviaDatabaseService openTriviaDatabaseService;

    @Autowired
    public TriviaRestController(OpenTriviaDatabaseService openTriviaDatabaseService) {
        this.openTriviaDatabaseService = openTriviaDatabaseService;
    }

    @GetMapping("/trivia")
    public List<TriviaDTO> getTrivia(@RequestParam(name = "numberOfTrivia") Integer numberOfTrivia) {
        LOG.info("Getting {} Trivia.", numberOfTrivia);
        return openTriviaDatabaseService.getTrivia(numberOfTrivia);
    }
}
