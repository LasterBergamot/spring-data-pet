package com.pet.springdata.service.trivia;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.Trivia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITriviaService {
    List<TriviaDTO> getTrivia(int numberOfTrivia);
    ResponseEntity<List<Trivia>> saveTrivia(int numberOfTrivia);

    List<Trivia> findTrivia(int numberOfTrivia);
}