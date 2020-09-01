package com.pet.springdata.domain.trivia.util;

import com.pet.springdata.domain.trivia.model.OpenTriviaDatabaseResult;
import com.pet.springdata.domain.trivia.model.resource.TriviaResource;
import com.pet.springdata.repository.trivia.model.Trivia;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenTriviaDatabaseUtil {

    public static List<OpenTriviaDatabaseResult> unescapeHtmlTagsOfAllTrivia(List<OpenTriviaDatabaseResult> results) {
        for (OpenTriviaDatabaseResult result : results) {
            unescapeHtmlTagsOfTrivia(result);
        }

        return results;
    }

    private static void unescapeHtmlTagsOfTrivia(OpenTriviaDatabaseResult result) {
        result.setCategory(HtmlUtils.htmlUnescape(result.getCategory()));
        result.setType(HtmlUtils.htmlUnescape(result.getType()));
        result.setDifficulty(HtmlUtils.htmlUnescape(result.getDifficulty()));
        result.setQuestion(HtmlUtils.htmlUnescape(result.getQuestion()));
        result.setCorrectAnswer(HtmlUtils.htmlUnescape(result.getCorrectAnswer()));
        result.setIncorrectAnswers(result.getIncorrectAnswers().stream().map(HtmlUtils::htmlUnescape).collect(Collectors.toList()));
    }

    public static List<Trivia> transformOpenTriviaDatabaseResultListToTriviaList(List<OpenTriviaDatabaseResult> results) {
        List<Trivia> triviaList = new ArrayList<>();

        for (OpenTriviaDatabaseResult result : results) {
            triviaList.add(transformOpenTriviaDatabaseResultToTrivia(result));
        }

        return triviaList;
    }

    public static Trivia transformOpenTriviaDatabaseResultToTrivia(OpenTriviaDatabaseResult result) {
        return new Trivia(
                result.getCategory(),
                result.getType(),
                result.getDifficulty(),
                result.getQuestion(),
                result.getCorrectAnswer(),
                result.getIncorrectAnswers()
        );
    }

    public static List<TriviaResource> transformTriviaListToTriviaResourceList(List<Trivia> triviaList) {
        List<TriviaResource> triviaResourceList = new ArrayList<>();

        for (Trivia trivia : triviaList) {
            triviaResourceList.add(transformTriviaToTriviaResource(trivia));
        }

        return triviaResourceList;
    }

    public static TriviaResource transformTriviaToTriviaResource(Trivia trivia) {
        return TriviaResource.builder()
                .id(trivia.getId())
                .category(trivia.getCategory())
                .type(trivia.getType())
                .difficulty(trivia.getDifficulty())
                .question(trivia.getQuestion())
                .correctAnswer(trivia.getCorrectAnswer())
                .incorrectAnswers(trivia.getIncorrectAnswers())
                .build();
    }

    public static Trivia transformTriviaResourceToTrivia(TriviaResource triviaResource) {
        return Trivia.builder()
                .id(triviaResource.getId())
                .category(triviaResource.getCategory())
                .type(triviaResource.getType())
                .difficulty(triviaResource.getDifficulty())
                .question(triviaResource.getQuestion())
                .correctAnswer(triviaResource.getCorrectAnswer())
                .incorrectAnswers(triviaResource.getIncorrectAnswers())
                .build();
    }
}
