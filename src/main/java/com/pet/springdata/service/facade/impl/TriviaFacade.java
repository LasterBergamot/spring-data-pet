package com.pet.springdata.service.facade.impl;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.Trivia;
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
        return openTriviaDatabaseService.getTrivia(numberOfTrivia);
    }

    @Override
    public ResponseEntity<List<Trivia>> saveTrivia(int numberOfTrivia) {
        return null;
    }
}
