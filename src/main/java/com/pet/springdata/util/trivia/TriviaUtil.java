package com.pet.springdata.util.trivia;

import com.pet.springdata.model.trivia.TriviaDTO;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TriviaUtil {

    private TriviaUtil() {}

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
}
