package com.pet.springdata.service.trivia;

import com.pet.springdata.model.trivia.TriviaDTO;

import java.util.List;

public interface OpenTriviaDatabaseService {
    List<TriviaDTO> getTrivia(Integer numberOfTrivia);
}
