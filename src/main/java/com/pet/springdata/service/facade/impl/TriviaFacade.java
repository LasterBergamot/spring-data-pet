package com.pet.springdata.service.facade.impl;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.Trivia;
import com.pet.springdata.service.facade.OpenTriviaDatabaseFacade;
import com.pet.springdata.service.trivia.ITriviaService;
import com.pet.springdata.service.trivia.IOpenTriviaDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriviaFacade implements OpenTriviaDatabaseFacade {

    private static final Logger LOG = LoggerFactory.getLogger(TriviaFacade.class);

    private final IOpenTriviaDatabaseService openTriviaDatabaseService;
    private final ITriviaService triviaService;

    @Autowired
    public TriviaFacade(IOpenTriviaDatabaseService openTriviaDatabaseService, ITriviaService triviaService) {
        this.openTriviaDatabaseService = openTriviaDatabaseService;
        this.triviaService = triviaService;
    }

    @Override
    public List<TriviaDTO> getTrivia(int numberOfTrivia) {
        return null;
    }

    @Override
    public ResponseEntity<List<Trivia>> saveTrivia(int numberOfTrivia) {
        return null;
    }
}
