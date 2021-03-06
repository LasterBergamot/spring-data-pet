package com.pet.springdata.web.controller.rest.answer;

import com.pet.springdata.domain.answer.AnswerService;
import com.pet.springdata.domain.answer.model.resource.AnswerResource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_ALL;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_ANSWER;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_CORRECTNESS;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_CORRECTNESS_USER;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_USER;
import static com.pet.springdata.web.util.Constants.REQUEST_PARAM_ANSWERED_CORRECTLY;
import static com.pet.springdata.web.util.Constants.REQUEST_PARAM_USER_ID;

@RestController
@RequestMapping(REQUEST_MAPPING_ANSWER)
@Slf4j
@RequiredArgsConstructor
public class AnswerRestController {

    @NonNull
    private final AnswerService answerService;

    @GetMapping(REQUEST_MAPPING_ALL)
    public List<AnswerResource> getAllAnswers() {
        log.info("Getting all Answers.");
        return answerService.getAllAnswers();
    }

    @GetMapping(REQUEST_MAPPING_CORRECTNESS)
    public ResponseEntity<List<AnswerResource>> getAllAnswersDependingOnCorrectness(@RequestParam(REQUEST_PARAM_ANSWERED_CORRECTLY) String answeredCorrectly) {
        log.info("Getting all Answers with correctness: {}", answeredCorrectly);
        return answerService.getAllAnswersDependingOnCorrectness(answeredCorrectly);
    }

    @GetMapping(REQUEST_MAPPING_USER)
    public ResponseEntity<List<AnswerResource>> getAllAnswersOfUser(@RequestParam(REQUEST_PARAM_USER_ID) String userId) {
        log.info("Getting all Answers of User with id: {}", userId);
        return answerService.getAllAnswersOfUser(userId);
    }

    @GetMapping(REQUEST_MAPPING_CORRECTNESS_USER)
    public ResponseEntity<List<AnswerResource>> getAllAnswersDependingOnCorrectnessAndUserId(@RequestParam(REQUEST_PARAM_ANSWERED_CORRECTLY) String answeredCorrectly,
                                                                                     @RequestParam(REQUEST_PARAM_USER_ID) String userId) {
        log.info("Getting all Answers with correctness: {}, and UserId: {}", answeredCorrectly, userId);
        return answerService.getAllAnswersDependingOnCorrectnessAndUserId(answeredCorrectly, userId);
    }
}
