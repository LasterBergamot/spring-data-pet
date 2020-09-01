package com.pet.springdata.domain.quiz.impl;

import com.pet.springdata.domain.answer.AnswerService;
import com.pet.springdata.domain.answer.model.PossibleAnswer;
import com.pet.springdata.domain.facade.OpenTriviaDatabaseFacade;
import com.pet.springdata.domain.quiz.QuizService;
import com.pet.springdata.domain.trivia.model.resource.TriviaResource;
import com.pet.springdata.domain.trivia.util.OpenTriviaDatabaseUtil;
import com.pet.springdata.domain.user.UserService;
import com.pet.springdata.domain.user.model.resource.UserResource;
import com.pet.springdata.domain.user.util.UserUtil;
import com.pet.springdata.repository.answer.model.Answer;
import com.pet.springdata.repository.user.model.Name;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class TriviaQuizService implements QuizService {

    @NonNull
    private final UserService userService;

    @NonNull
    private final OpenTriviaDatabaseFacade openTriviaDatabaseFacade;

    @NonNull
    private final AnswerService answerService;

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        UserResource userResource = findById((short) 1);

        printWelcomeInformation(userResource.getName());
        printQuestions(getDesiredNumberOfTriviaFromTheDatabase(scanner), scanner, userResource);
    }

    private UserResource findById(short id) {
        return userService.findById(id);
    }

    private void printWelcomeInformation(Name name) {
        System.out.println("Welcome to the Trivia Quiz Application!");
        System.out.printf("You are playing as %s %s.%n", name.getFirstName(), name.getLastName());
    }

    private List<TriviaResource> getDesiredNumberOfTriviaFromTheDatabase(Scanner scanner) {
        System.out.println("How many questions would you like to answer?");
        int desiredNumberOfQuestions = scanner.nextInt();

        System.out.printf("Getting %s questions from the database.%n", desiredNumberOfQuestions);
        return openTriviaDatabaseFacade.findTrivia(desiredNumberOfQuestions);
    }

    private void printQuestions(List<TriviaResource> triviaResourceList, Scanner scanner, UserResource userResource) {
        System.out.println("Printing the questions:");
        int triviaListSize = triviaResourceList.size();

        for (int numberOfQuestion = 1; numberOfQuestion <= triviaListSize; numberOfQuestion++) {
            TriviaResource triviaResource = triviaResourceList.get(numberOfQuestion - 1);
            String correctAnswer = triviaResource.getCorrectAnswer();
            List<PossibleAnswer> possibleAnswers = createPossibleAnswersFromTriviaAnswers(correctAnswer, triviaResource.getIncorrectAnswers());
            printTrivia(numberOfQuestion, triviaResource, possibleAnswers);

            PossibleAnswer selectedAnswer = selectAnswer(scanner, possibleAnswers);
            String answer = selectedAnswer.getAnswer();
            boolean answeredCorrectly = answer.equals(correctAnswer);
            printMessageDependingOnIfTheUserAnsweredCorrectly(answeredCorrectly, correctAnswer);

            saveAnswer(userResource, triviaResource, answer, answeredCorrectly);
            printMessageDependingOnTheRemainingQuestions(numberOfQuestion, triviaListSize);
        }
    }

    private List<PossibleAnswer> createPossibleAnswersFromTriviaAnswers(String correctAnswer, List<String> incorrectAnswers) {
        List<String> possibleAnswers = new ArrayList<>(incorrectAnswers);
        possibleAnswers.add(correctAnswer);
        Collections.shuffle(possibleAnswers);

        List<PossibleAnswer> result = new ArrayList<>();
        for (int index = 0; index < possibleAnswers.size(); index++) {
            int numberOfAnswer = index + 1;
            result.add(new PossibleAnswer(numberOfAnswer, possibleAnswers.get(index)));
        }

        return result;
    }

    private void printTrivia(int numberOfQuestion, TriviaResource triviaResource, List<PossibleAnswer> possibleAnswers) {
        System.out.printf("Question #%d%n", numberOfQuestion);
        System.out.printf("Question: %s%n", triviaResource.getQuestion());
        System.out.printf("Category: %s%n", triviaResource.getCategory());
        System.out.printf("Type: %s%n", triviaResource.getType());
        System.out.printf("Difficulty: %s%n", triviaResource.getDifficulty());
        System.out.println("Possible answers:");
        possibleAnswers.forEach(System.out::println);
    }

    private PossibleAnswer selectAnswer(Scanner scanner, List<PossibleAnswer> possibleAnswers) {
        System.out.println("Which answer would you like to choose?");
        int numberOfSelectedAnswer = scanner.nextInt();
        PossibleAnswer selectedAnswer = getSelectedAnswerFromPossibleAnswers(numberOfSelectedAnswer, possibleAnswers);
        System.out.printf("You selected answer #%d - %s%n", selectedAnswer.getIndexOfAnswer(), selectedAnswer.getAnswer());

        return selectedAnswer;
    }

    private PossibleAnswer getSelectedAnswerFromPossibleAnswers(int numberOfSelectedAnswer, List<PossibleAnswer> possibleAnswers) {
        PossibleAnswer selectedAnswer = new PossibleAnswer(-1, "Default Answer");

        for (PossibleAnswer possibleAnswer : possibleAnswers) {
            if (possibleAnswer.getIndexOfAnswer() == numberOfSelectedAnswer) {
                selectedAnswer = possibleAnswer;
                break;
            }
        }

        return selectedAnswer;
    }

    private void printMessageDependingOnIfTheUserAnsweredCorrectly(boolean answeredCorrectly, String correctAnswer) {
        if (answeredCorrectly) {
            System.out.println("You answered correctly!");
        } else {
            System.out.println("Your answer is wrong!");
            System.out.printf("The correct answer is: %s%n", correctAnswer);
        }
    }

    private void saveAnswer(UserResource userResource, TriviaResource triviaResource, String selectedAnswer, boolean answeredCorrectly) {
        System.out.println("Saving your answer to the database.");
        answerService.saveAnswer(new Answer(UserUtil.transformUserResourceToUser(userResource), OpenTriviaDatabaseUtil.transformTriviaResourceToTrivia(triviaResource), selectedAnswer, answeredCorrectly));
    }

    private void printMessageDependingOnTheRemainingQuestions(int indexOfQuestion, int triviaListSize) {
        if (indexOfQuestion == triviaListSize) {
            System.out.println("This was the last question, good bye!");
            System.exit(0);
        } else {
            System.out.println("Next question!\n");
        }
    }
}
