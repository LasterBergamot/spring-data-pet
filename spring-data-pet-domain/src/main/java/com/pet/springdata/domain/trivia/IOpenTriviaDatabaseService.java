package com.pet.springdata.domain.trivia;

import com.pet.springdata.domain.trivia.model.OpenTriviaDatabaseResult;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IOpenTriviaDatabaseService {
    ResponseEntity<List<OpenTriviaDatabaseResult>> getTrivia(int numberOfTrivia);
}
