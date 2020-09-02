package com.pet.springdata.domain.answer.util;

import com.pet.springdata.domain.answer.model.resource.AnswerResource;
import com.pet.springdata.repository.answer.model.Answer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerMapper {

    public List<AnswerResource> transformAnswerListToAnswerResourceList(List<Answer> answerList) {
        return answerList.stream()
                .map(this::transformAnswerToAnswerResource)
                .collect(Collectors.toList());
    }

    public AnswerResource transformAnswerToAnswerResource(Answer answer) {
        return AnswerResource.builder()
                .id(answer.getId())
                .user(answer.getUser())
                .trivia(answer.getTrivia())
                .selectedAnswer(answer.getSelectedAnswer())
                .answeredCorrectly(answer.getAnsweredCorrectly())
                .build();
    }
}
