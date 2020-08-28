package com.pet.springdata.service.facade.impl;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.model.Trivia;
import com.pet.springdata.service.facade.OpenTriviaDatabaseFacade;
import com.pet.springdata.service.trivia.IOpenTriviaDatabaseService;
import com.pet.springdata.service.trivia.ITriviaService;
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
    public ResponseEntity<List<TriviaDTO>> getTrivia(int numberOfTrivia) {
        log.info("Getting {} Trivia.", numberOfTrivia);
        return openTriviaDatabaseService.getTrivia(numberOfTrivia);
    }

    @Override
    public ResponseEntity<List<Trivia>> saveTrivia(int numberOfTrivia) {
        log.info("Saving {} Trivia.", numberOfTrivia);
        ResponseEntity<List<TriviaDTO>> triviaDTOListResponse = openTriviaDatabaseService.getTrivia(numberOfTrivia);

        return triviaService.saveTrivia(triviaDTOListResponse.getBody());
    }

    @Override
    public List<Trivia> findTrivia(int numberOfTrivia) {
        log.info("Getting {} Trivia from the database.", numberOfTrivia);

        return triviaService.findTrivia(numberOfTrivia);
    }

    @Override
    public ResponseEntity<List<Trivia>> findTriviaByCategoryTypeAndDifficulty(String category, String type, String difficulty) {
        log.info("Getting Trivia with category: {}, type: {}, and difficulty: {}.", category, type, difficulty);
        return triviaService.findTriviaByCategoryTypeAndDifficulty(category, type, difficulty);
    }
}
