package com.pet.springdata;

import com.pet.springdata.repository.answer.Answer;
import com.pet.springdata.repository.answer.PossibleAnswer;
import com.pet.springdata.repository.trivia.Trivia;
import com.pet.springdata.repository.user.Name;
import com.pet.springdata.repository.user.User;
import com.pet.springdata.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	@Autowired
	private QuizService quizService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = quizService.findById((short) 1);
		printWelcomeInformation(user.getName());

		Scanner scanner = new Scanner(System.in);
		List<Trivia> triviaList = getDesiredNumberOfTriviaFromTheDatabase(scanner);
		System.out.println("Printing the questions:");
		for (int index = 0; index < triviaList.size(); index++) {
			Trivia trivia = triviaList.get(index);
			int numberOfQuestion = index + 1;
			List<PossibleAnswer> possibleAnswers = createPossibleAnswersFromTriviaAnswers(trivia.getCorrectAnswer(), trivia.getIncorrectAnswers());
			printTrivia(numberOfQuestion, trivia, possibleAnswers);

			System.out.println("Which answer would you like to choose?");
			int numberOfSelectedAnswer = scanner.nextInt();
			PossibleAnswer selectedAnswer = getSelectedAnswerFromPossibleAnswers(numberOfSelectedAnswer, possibleAnswers);
			System.out.println(String.format("You selected answer #%d - %s", selectedAnswer.getIndexOfAnswer(), selectedAnswer.getAnswer()));
			boolean answeredCorrectly = selectedAnswer.getAnswer().equals(trivia.getCorrectAnswer());
			if (answeredCorrectly) {
				System.out.println("You answered correctly!");
			} else {
				System.out.println("Your answer is wrong!");
				System.out.println(String.format("The correct answer is: %s", trivia.getCorrectAnswer()));
			}

			System.out.println("Saving your answer to the database.");
			Answer answer = new Answer(user, trivia, selectedAnswer.getAnswer(), answeredCorrectly);
			quizService.saveAnswer(answer);

			if (index == triviaList.size() - 1) {
				System.out.println("This was the last question, good bye!");
				System.exit(0);
			} else {
				System.out.println("Next question!\n");
			}
		}
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

	private void printWelcomeInformation(Name name) {
		System.out.println("Welcome to the Trivia Quiz Application!");
		System.out.println(String.format("You are playing as %s %s.", name.getFirstName(), name.getLastName()));
	}

	private List<Trivia> getDesiredNumberOfTriviaFromTheDatabase(Scanner scanner) {
		System.out.println("How many questions would you like to answer?");
		int desiredNumberOfQuestions = scanner.nextInt();

		System.out.println(String.format("Getting %s questions from the database.", desiredNumberOfQuestions));
		return quizService.findTrivia(desiredNumberOfQuestions);
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
		System.out.println(String.format("Question #%d", numberOfQuestion));
		System.out.println(String.format("Question: %s", trivia.getQuestion()));
		System.out.println(String.format("Category: %s", trivia.getCategory()));
		System.out.println(String.format("Type: %s", trivia.getType()));
		System.out.println(String.format("Difficulty: %s", trivia.getDifficulty()));
		System.out.println("Possible answers:");
		possibleAnswers.forEach(System.out::println);
	}
}
