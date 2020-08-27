package com.pet.springdata.util.trivia;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.model.Trivia;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TriviaUtil {

    public static List<TriviaDTO> unescapeHtmlTagsOfAllTrivia(List<TriviaDTO> results) {
        for (TriviaDTO result : results) {
            unescapeHtmlTagsOfTrivia(result);
        }

        return results;
    }

    private static void unescapeHtmlTagsOfTrivia(TriviaDTO result) {
        result.setCategory(HtmlUtils.htmlUnescape(result.getCategory()));
        result.setType(HtmlUtils.htmlUnescape(result.getType()));
        result.setDifficulty(HtmlUtils.htmlUnescape(result.getDifficulty()));
        result.setQuestion(HtmlUtils.htmlUnescape(result.getQuestion()));
        result.setCorrectAnswer(HtmlUtils.htmlUnescape(result.getCorrectAnswer()));
        result.setIncorrectAnswers(result.getIncorrectAnswers().stream().map(HtmlUtils::htmlUnescape).collect(Collectors.toList()));
    }

    public static List<Trivia> transformTriviaDTOListToTriviaList(List<TriviaDTO> triviaDTOList) {
        List<Trivia> triviaList = new ArrayList<>();

        for (TriviaDTO triviaDTO : triviaDTOList) {
            triviaList.add(transformTriviaDTOToTrivia(triviaDTO));
        }

        return triviaList;
    }

    public static Trivia transformTriviaDTOToTrivia(TriviaDTO triviaDTO) {
        return new Trivia(
                triviaDTO.getCategory(),
                triviaDTO.getType(),
                triviaDTO.getDifficulty(),
                triviaDTO.getQuestion(),
                triviaDTO.getCorrectAnswer(),
                triviaDTO.getIncorrectAnswers()
        );
    }
}
