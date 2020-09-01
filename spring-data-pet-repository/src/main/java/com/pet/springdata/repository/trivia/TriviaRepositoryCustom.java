package com.pet.springdata.repository.trivia;

import com.pet.springdata.repository.trivia.model.Trivia;

import java.util.List;

public interface TriviaRepositoryCustom {
    List<Trivia> findTriviaByCategoryTypeAndDifficulty(String category, String type, String difficulty);
}
