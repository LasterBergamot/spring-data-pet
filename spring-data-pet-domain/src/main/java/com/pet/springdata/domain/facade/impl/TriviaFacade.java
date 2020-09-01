package com.pet.springdata.domain.facade.impl;

import com.pet.springdata.domain.trivia.model.OpenTriviaDatabaseResult;
import com.pet.springdata.domain.trivia.model.resource.TriviaResource;
import com.pet.springdata.repository.trivia.model.Trivia;
import com.pet.springdata.domain.facade.OpenTriviaDatabaseFacade;
import com.pet.springdata.domain.trivia.IOpenTriviaDatabaseService;
import com.pet.springdata.domain.trivia.ITriviaService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TriviaFacade implements OpenTriviaDatabaseFacade {

    @NonNull
    private final IOpenTriviaDatabaseService openTriviaDatabaseService;

    @NonNull
    private final ITriviaService triviaService;

    @Override
    public ResponseEntity<List<OpenTriviaDatabaseResult>> getTrivia(int numberOfTrivia) {
        log.info("Getting {} Trivia.", numberOfTrivia);
        return openTriviaDatabaseService.getTrivia(numberOfTrivia);
    }

    @Override
    public ResponseEntity<List<TriviaResource>> saveTrivia(int numberOfTrivia) {
        log.info("Saving {} Trivia.", numberOfTrivia);
        ResponseEntity<List<OpenTriviaDatabaseResult>> triviaDTOListResponse = openTriviaDatabaseService.getTrivia(numberOfTrivia);

        return triviaService.saveTrivia(triviaDTOListResponse.getBody());
    }

    @Override
    public List<TriviaResource> findTrivia(int numberOfTrivia) {
        log.info("Getting {} Trivia from the database.", numberOfTrivia);

        return triviaService.findTrivia(numberOfTrivia);
    }

    @Override
    public List<TriviaResource> findAllTrivia() {
        log.info("Finding all Trivia.");
        return triviaService.findAllTrivia();
    }


    @Override
    public ResponseEntity<List<TriviaResource>> findTriviaByCategoryTypeAndDifficulty(String category, String type, String difficulty) {
        log.info("Getting Trivia with category: {}, type: {}, and difficulty: {}.", category, type, difficulty);
        return triviaService.findTriviaByCategoryTypeAndDifficulty(category, type, difficulty);
    }
}
