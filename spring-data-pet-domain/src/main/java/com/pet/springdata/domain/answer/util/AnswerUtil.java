package com.pet.springdata.domain.answer.util;

import com.pet.springdata.domain.answer.model.resource.AnswerResource;
import com.pet.springdata.repository.answer.model.Answer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerUtil {

    public static List<AnswerResource> transformAnswerListToAnswerResourceList(List<Answer> answerList) {
        List<AnswerResource> answerResourceList = new ArrayList<>();

        for (Answer answer : answerList) {
            answerResourceList.add(transformAnswerToAnswerResource(answer));
        }

        return answerResourceList;
    }

    public static AnswerResource transformAnswerToAnswerResource(Answer answer) {
        return AnswerResource.builder()
                .id(answer.getId())
                .user(answer.getUser())
                .trivia(answer.getTrivia())
                .selectedAnswer(answer.getSelectedAnswer())
                .answeredCorrectly(answer.getAnsweredCorrectly())
                .build();
    }
}
