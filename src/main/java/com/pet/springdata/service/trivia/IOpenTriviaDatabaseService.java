package com.pet.springdata.service.trivia;

import com.pet.springdata.model.trivia.TriviaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOpenTriviaDatabaseService {
    ResponseEntity<List<TriviaDTO>> getTrivia(int numberOfTrivia);
}
