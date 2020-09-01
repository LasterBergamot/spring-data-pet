package com.pet.springdata.domain.trivia;

import com.pet.springdata.domain.trivia.model.TriviaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOpenTriviaDatabaseService {
    ResponseEntity<List<TriviaDTO>> getTrivia(int numberOfTrivia);
}
