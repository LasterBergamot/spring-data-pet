package com.pet.springdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pet.springdata")
public class SpringDataApplication {

//	@Autowired
//	private QuizService quizService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		quizService.play();
//	}
}
