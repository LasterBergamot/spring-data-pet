package com.pet.springdata.domain.trivia;

import com.pet.springdata.domain.trivia.model.OpenTriviaDatabaseResult;
import com.pet.springdata.domain.trivia.model.resource.TriviaResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITriviaService {
    ResponseEntity<List<TriviaResource>> saveTrivia(List<OpenTriviaDatabaseResult> openTriviaDatabaseResultList);

    List<TriviaResource> findTrivia(int numberOfTrivia);
    List<TriviaResource> findAllTrivia();

    ResponseEntity<List<TriviaResource>> findTriviaByCategoryTypeAndDifficulty(String category, String type, String difficulty);
}
