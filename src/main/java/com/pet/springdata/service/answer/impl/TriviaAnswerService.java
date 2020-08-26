package com.pet.springdata.service.answer.impl;

import com.pet.springdata.repository.answer.Answer;
import com.pet.springdata.repository.answer.AnswerRepository;
import com.pet.springdata.service.answer.AnswerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TriviaAnswerService implements AnswerService {

    @NonNull
    private final AnswerRepository answerRepository;

    @Override
    public void saveAnswer(Answer answer) {
        log.info("Saving Answer: {}", answer);
        answerRepository.save(answer);
    }
}
