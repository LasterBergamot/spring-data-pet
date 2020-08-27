package com.pet.springdata.controller.answer;

import com.pet.springdata.repository.answer.model.Answer;
import com.pet.springdata.service.answer.AnswerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/answer")
@Slf4j
@RequiredArgsConstructor
public class AnswerRestController {

    @NonNull
    private final AnswerService answerService;

    @GetMapping("/all")
    public ResponseEntity<List<Answer>> getAllAnswers() {
        log.info("Getting all Answers.");
        return answerService.getAllAnswers();
    }

    @GetMapping("/correctness")
    public ResponseEntity<List<Answer>> getAllAnswersDependingOnCorrectness(@RequestParam("answeredCorrectly") String answeredCorrectly) {
        log.info("Getting all Answers with correctness: {}", answeredCorrectly);
        return answerService.getAllAnswersDependingOnCorrectness(Boolean.parseBoolean(answeredCorrectly));
    }

    @GetMapping("/user")
    public ResponseEntity<List<Answer>> getAllAnswersOfUser(@RequestParam("userId") String userId) {
        log.info("Getting all Answers of User with id: {}", userId);
        return answerService.getAllAnswersOfUser(Short.parseShort(userId));
    }
}
