package com.pet.springdata.domain.trivia.util;

import com.pet.springdata.domain.trivia.model.OpenTriviaDatabaseResult;
import com.pet.springdata.domain.trivia.model.resource.TriviaResource;
import com.pet.springdata.repository.trivia.model.Trivia;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OpenTriviaDatabaseMapper {

    public List<OpenTriviaDatabaseResult> unescapeHtmlTagsOfAllTrivia(List<OpenTriviaDatabaseResult> results) {
        return results.stream()
                .map(this::unescapeHtmlTagsOfTrivia)
                .collect(Collectors.toList());
    }

    private OpenTriviaDatabaseResult unescapeHtmlTagsOfTrivia(OpenTriviaDatabaseResult result) {
        return OpenTriviaDatabaseResult.builder()
                .category(HtmlUtils.htmlUnescape(result.getCategory()))
                .type(HtmlUtils.htmlUnescape(result.getType()))
                .difficulty(HtmlUtils.htmlUnescape(result.getDifficulty()))
                .question(HtmlUtils.htmlUnescape(result.getQuestion()))
                .correctAnswer(HtmlUtils.htmlUnescape(result.getCorrectAnswer()))
                .incorrectAnswers(result.getIncorrectAnswers().stream().map(HtmlUtils::htmlUnescape).collect(Collectors.toList()))
                .build();
    }

    public List<Trivia> transformOpenTriviaDatabaseResultListToTriviaList(List<OpenTriviaDatabaseResult> results) {
        return results.stream()
                .map(this::transformOpenTriviaDatabaseResultToTrivia)
                .collect(Collectors.toList());
    }

    public Trivia transformOpenTriviaDatabaseResultToTrivia(OpenTriviaDatabaseResult result) {
        return new Trivia(
                result.getCategory(),
                result.getType(),
                result.getDifficulty(),
                result.getQuestion(),
                result.getCorrectAnswer(),
                result.getIncorrectAnswers()
        );
    }

    public List<TriviaResource> transformTriviaListToTriviaResourceList(List<Trivia> triviaList) {
        return triviaList.stream()
                .map(this::transformTriviaToTriviaResource)
                .collect(Collectors.toList());
    }

    public TriviaResource transformTriviaToTriviaResource(Trivia trivia) {
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

    public Trivia transformTriviaResourceToTrivia(TriviaResource triviaResource) {
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
