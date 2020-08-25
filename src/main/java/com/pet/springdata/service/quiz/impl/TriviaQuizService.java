package com.pet.springdata.service.quiz.impl;

import com.pet.springdata.repository.answer.Answer;
import com.pet.springdata.repository.trivia.Trivia;
import com.pet.springdata.repository.user.User;
import com.pet.springdata.service.answer.AnswerService;
import com.pet.springdata.service.quiz.QuizService;
import com.pet.springdata.service.trivia.OpenTriviaDatabaseService;
import com.pet.springdata.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriviaQuizService implements QuizService {

    private static final Logger LOG = LoggerFactory.getLogger(TriviaQuizService.class);

    private final UserService userService;
    private final OpenTriviaDatabaseService openTriviaDatabaseService;
    private final AnswerService answerService;

    @Autowired
    public TriviaQuizService(UserService userService, OpenTriviaDatabaseService openTriviaDatabaseService, AnswerService answerService) {
        this.userService = userService;
        this.openTriviaDatabaseService = openTriviaDatabaseService;
        this.answerService = answerService;
    }

    @Override
    public User findById(short id) {
        LOG.info("Finding User with id: {}", id);

        return userService.findById(id);
    }

    @Override
    public List<Trivia> findTrivia(int numberOfTrivia) {
        LOG.info("Finding {} Trivia.", numberOfTrivia);

        return openTriviaDatabaseService.findTrivia(numberOfTrivia);
    }

    @Override
    public void saveAnswer(Answer answer) {
        LOG.info("Saving Answer: {}.", answer);

        answerService.saveAnswer(answer);
    }
}
