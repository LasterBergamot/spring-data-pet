package com.pet.springdata.domain.facade;

import com.pet.springdata.domain.trivia.model.OpenTriviaDatabaseResult;
import com.pet.springdata.domain.trivia.model.resource.TriviaResource;
import com.pet.springdata.repository.trivia.model.Trivia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OpenTriviaDatabaseFacade {
    ResponseEntity<List<OpenTriviaDatabaseResult>> getTrivia(int numberOfTrivia);
    ResponseEntity<List<TriviaResource>> saveTrivia(int numberOfTrivia);

    List<TriviaResource> findTrivia(int numberOfTrivia);
    List<TriviaResource> findAllTrivia();

    ResponseEntity<List<TriviaResource>> findTriviaByCategoryTypeAndDifficulty(String category, String type, String difficulty);
}
