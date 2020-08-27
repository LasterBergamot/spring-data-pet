package com.pet.springdata.service.answer;

import com.pet.springdata.repository.answer.model.Answer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnswerService {
    void saveAnswer(Answer answer);

    ResponseEntity<List<Answer>> getAllAnswers();
    ResponseEntity<List<Answer>> getAllAnswersDependingOnCorrectness(String answeredCorrectly);
    ResponseEntity<List<Answer>> getAllAnswersOfUser(String userId);
    ResponseEntity<List<Answer>> getAllAnswersDependingOnCorrectnessAndUserId(String answeredCorrectly, String userId);
}
