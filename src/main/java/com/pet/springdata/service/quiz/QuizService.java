package com.pet.springdata.service.quiz;

import com.pet.springdata.repository.answer.Answer;
import com.pet.springdata.repository.trivia.Trivia;
import com.pet.springdata.repository.user.User;

import java.util.List;

public interface QuizService {
    User findById(short id);
    List<Trivia> findTrivia(int numberOfTrivia);
    void saveAnswer(Answer answer);
}
