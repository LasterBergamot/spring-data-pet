package com.pet.springdata.service.quiz.impl;

import com.pet.springdata.repository.answer.Answer;
import com.pet.springdata.repository.answer.PossibleAnswer;
import com.pet.springdata.repository.trivia.Trivia;
import com.pet.springdata.repository.user.Name;
import com.pet.springdata.repository.user.User;
import com.pet.springdata.service.answer.AnswerService;
import com.pet.springdata.service.quiz.QuizService;
import com.pet.springdata.service.trivia.IOpenTriviaDatabaseService;
import com.pet.springdata.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Service
public class TriviaQuizService implements QuizService {

    private final UserService userService;
    private final IOpenTriviaDatabaseService openTriviaDatabaseService;
    private final AnswerService answerService;

    @Autowired
    public TriviaQuizService(UserService userService, IOpenTriviaDatabaseService openTriviaDatabaseService, AnswerService answerService) {
        this.userService = userService;
        this.openTriviaDatabaseService = openTriviaDatabaseService;
        this.answerService = answerService;
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        User user = findById((short) 1);

        printWelcomeInformation(user.getName());
        printQuestions(getDesiredNumberOfTriviaFromTheDatabase(scanner), scanner, user);
    }

    private User findById(short id) {
        return userService.findById(id);
    }

    private void printWelcomeInformation(Name name) {
        System.out.println("Welcome to the Trivia Quiz Application!");
        System.out.printf("You are playing as %s %s.%n", name.getFirstName(), name.getLastName());
    }

    private List<Trivia> getDesiredNumberOfTriviaFromTheDatabase(Scanner scanner) {
        System.out.println("How many questions would you like to answer?");
        int desiredNumberOfQuestions = scanner.nextInt();

        System.out.printf("Getting %s questions from the database.%n", desiredNumberOfQuestions);
        return openTriviaDatabaseService.findTrivia(desiredNumberOfQuestions);
    }

    private void printQuestions(List<Trivia> triviaList, Scanner scanner, User user) {
        System.out.println("Printing the questions:");
        int triviaListSize = triviaList.size();

        for (int numberOfQuestion = 1; numberOfQuestion <= triviaListSize; numberOfQuestion++) {
            Trivia trivia = triviaList.get(numberOfQuestion - 1);
            String correctAnswer = trivia.getCorrectAnswer();
            List<PossibleAnswer> possibleAnswers = createPossibleAnswersFromTriviaAnswers(correctAnswer, trivia.getIncorrectAnswers());
            printTrivia(numberOfQuestion, trivia, possibleAnswers);

            PossibleAnswer selectedAnswer = selectAnswer(scanner, possibleAnswers);
            String answer = selectedAnswer.getAnswer();
            boolean answeredCorrectly = answer.equals(correctAnswer);
            printMessageDependingOnIfTheUserAnsweredCorrectly(answeredCorrectly, correctAnswer);

            saveAnswer(user, trivia, answer, answeredCorrectly);
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

    private void printTrivia(int numberOfQuestion, Trivia trivia, List<PossibleAnswer> possibleAnswers) {
        System.out.printf("Question #%d%n", numberOfQuestion);
        System.out.printf("Question: %s%n", trivia.getQuestion());
        System.out.printf("Category: %s%n", trivia.getCategory());
        System.out.printf("Type: %s%n", trivia.getType());
        System.out.printf("Difficulty: %s%n", trivia.getDifficulty());
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

    private void saveAnswer(User user, Trivia trivia, String selectedAnswer, boolean answeredCorrectly) {
        System.out.println("Saving your answer to the database.");
        answerService.saveAnswer(new Answer(user, trivia, selectedAnswer, answeredCorrectly));
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
