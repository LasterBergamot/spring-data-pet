package com.pet.springdata.service.facade;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.Trivia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OpenTriviaDatabaseFacade {
    ResponseEntity<List<TriviaDTO>> getTrivia(int numberOfTrivia);
    ResponseEntity<List<Trivia>> saveTrivia(int numberOfTrivia);
}
