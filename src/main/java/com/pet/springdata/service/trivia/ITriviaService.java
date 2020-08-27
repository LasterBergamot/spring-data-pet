package com.pet.springdata.service.trivia;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.model.Trivia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ITriviaService {
    ResponseEntity<List<Trivia>> saveTrivia(List<TriviaDTO> triviaDTOList);

    List<Trivia> findTrivia(int numberOfTrivia);
}
