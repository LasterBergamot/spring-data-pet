package com.pet.springdata.domain.answer;

import com.pet.springdata.domain.answer.model.resource.AnswerResource;
import com.pet.springdata.repository.answer.model.Answer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnswerService {
    AnswerResource saveAnswer(Answer answer);

    List<AnswerResource> getAllAnswers();
    ResponseEntity<List<AnswerResource>> getAllAnswersDependingOnCorrectness(String answeredCorrectly);
    ResponseEntity<List<AnswerResource>> getAllAnswersOfUser(String userId);
    ResponseEntity<List<AnswerResource>> getAllAnswersDependingOnCorrectnessAndUserId(String answeredCorrectly, String userId);
}
