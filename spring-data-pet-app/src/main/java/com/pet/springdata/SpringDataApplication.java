package com.pet.springdata;

import com.pet.springdata.domain.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pet.springdata")
public class SpringDataApplication implements CommandLineRunner {

	@Autowired
	private QuizService quizService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		quizService.play();
	}
}
