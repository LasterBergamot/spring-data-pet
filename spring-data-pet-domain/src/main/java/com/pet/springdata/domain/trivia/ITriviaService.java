package com.pet.springdata.domain.trivia;

import com.pet.springdata.domain.trivia.model.TriviaDTO;
import com.pet.springdata.repository.trivia.model.Trivia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITriviaService {
    ResponseEntity<List<Trivia>> saveTrivia(List<TriviaDTO> triviaDTOList);

    List<Trivia> findTrivia(int numberOfTrivia);
    List<Trivia> findAllTrivia();

    ResponseEntity<List<Trivia>> findTriviaByCategoryTypeAndDifficulty(String category, String type, String difficulty);
}
