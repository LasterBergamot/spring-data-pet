package com.pet.springdata.domain.facade;

import com.pet.springdata.domain.trivia.model.TriviaDTO;
import com.pet.springdata.repository.trivia.model.Trivia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OpenTriviaDatabaseFacade {
    ResponseEntity<List<TriviaDTO>> getTrivia(int numberOfTrivia);
    ResponseEntity<List<Trivia>> saveTrivia(int numberOfTrivia);

    List<Trivia> findTrivia(int numberOfTrivia);
    List<Trivia> findAllTrivia();

    ResponseEntity<List<Trivia>> findTriviaByCategoryTypeAndDifficulty(String category, String type, String difficulty);
}
