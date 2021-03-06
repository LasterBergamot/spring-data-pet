package com.pet.springdata.web.controller.rest.trivia;

import com.pet.springdata.domain.facade.OpenTriviaDatabaseFacade;
import com.pet.springdata.domain.trivia.model.OpenTriviaDatabaseResult;
import com.pet.springdata.domain.trivia.model.resource.TriviaResource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_ALL;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_DETAILS;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_GET_TRIVIA;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_SAVE_TRIVIA;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_TRIVIA;
import static com.pet.springdata.web.util.Constants.REQUEST_PARAM_CATEGORY;
import static com.pet.springdata.web.util.Constants.REQUEST_PARAM_DIFFICULTY;
import static com.pet.springdata.web.util.Constants.REQUEST_PARAM_NUMBER_OF_TRIVIA;
import static com.pet.springdata.web.util.Constants.REQUEST_PARAM_TYPE;

@RestController
@RequestMapping(REQUEST_MAPPING_TRIVIA)
@RequiredArgsConstructor
@Slf4j
public class TriviaRestController {

    @NonNull
    private final OpenTriviaDatabaseFacade openTriviaDatabaseFacade;

    @GetMapping(REQUEST_MAPPING_GET_TRIVIA)
    public ResponseEntity<List<OpenTriviaDatabaseResult>> getTrivia(@RequestParam(REQUEST_PARAM_NUMBER_OF_TRIVIA) int numberOfTrivia) {
        log.info("Getting {} Trivia.", numberOfTrivia);
        return openTriviaDatabaseFacade.getTrivia(numberOfTrivia);
    }

    //TODO: do not send back entity
    @PostMapping(REQUEST_MAPPING_SAVE_TRIVIA)
    public ResponseEntity<List<TriviaResource>> saveTrivia(@RequestParam(REQUEST_PARAM_NUMBER_OF_TRIVIA) int numberOfTrivia) {
        log.info("Saving {} Trivia.", numberOfTrivia);
        return openTriviaDatabaseFacade.saveTrivia(numberOfTrivia);
    }

    @GetMapping(REQUEST_MAPPING_ALL)
    public List<TriviaResource> findAllTrivia() {
        log.info("Finding all Trivia.");
        return openTriviaDatabaseFacade.findAllTrivia();
    }

    @GetMapping(REQUEST_MAPPING_DETAILS)
    public ResponseEntity<List<TriviaResource>> findTriviaByCategoryTypeAndDifficulty(@RequestParam(value = REQUEST_PARAM_CATEGORY, required = false) String category,
                                                                              @RequestParam(value = REQUEST_PARAM_TYPE, required = false) String type,
                                                                              @RequestParam(value = REQUEST_PARAM_DIFFICULTY, required = false) String difficulty) {
        log.info("Getting Trivia with category: {}, type: {}, and difficulty: {}.", category, type, difficulty);
        return openTriviaDatabaseFacade.findTriviaByCategoryTypeAndDifficulty(category, type, difficulty);
    }
}
