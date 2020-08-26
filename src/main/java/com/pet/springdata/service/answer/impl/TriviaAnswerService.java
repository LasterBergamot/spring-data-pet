package com.pet.springdata.service.answer.impl;

import com.pet.springdata.repository.answer.Answer;
import com.pet.springdata.repository.answer.AnswerRepository;
import com.pet.springdata.service.answer.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TriviaAnswerService implements AnswerService {

    private static final Logger LOG = LoggerFactory.getLogger(TriviaAnswerService.class);

    private final AnswerRepository answerRepository;

    @Autowired
    public TriviaAnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public void saveAnswer(Answer answer) {
        LOG.info("Saving Answer: {}", answer);

        answerRepository.save(answer);
    }
}
