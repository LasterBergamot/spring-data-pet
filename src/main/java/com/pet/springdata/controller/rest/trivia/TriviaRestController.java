package com.pet.springdata.controller.rest.trivia;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.model.Trivia;
import com.pet.springdata.service.facade.OpenTriviaDatabaseFacade;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trivia")
@RequiredArgsConstructor
@Slf4j
public class TriviaRestController {

    @NonNull
    private final OpenTriviaDatabaseFacade openTriviaDatabaseFacade;

    @GetMapping("/getTrivia")
    public ResponseEntity<List<TriviaDTO>> getTrivia(@RequestParam("numberOfTrivia") int numberOfTrivia) {
        log.info("Getting {} Trivia.", numberOfTrivia);
        return openTriviaDatabaseFacade.getTrivia(numberOfTrivia);
    }

    //TODO: do not send back entity
    @GetMapping("/saveTrivia")
    public ResponseEntity<List<Trivia>> saveTrivia(@RequestParam("numberOfTrivia") int numberOfTrivia) {
        log.info("Saving {} Trivia.", numberOfTrivia);
        return openTriviaDatabaseFacade.saveTrivia(numberOfTrivia);
    }

    @GetMapping("/details")
    public ResponseEntity<List<Trivia>> findTriviaByCategoryTypeAndDifficulty(@RequestParam(value = "category", required = false) String category,
                                                                              @RequestParam(value = "type", required = false) String type,
                                                                              @RequestParam(value = "difficulty", required = false) String difficulty) {
        log.info("Getting Trivia with category: {}, type: {}, and difficulty: {}.", category, type, difficulty);
        return openTriviaDatabaseFacade.findTriviaByCategoryTypeAndDifficulty(category, type, difficulty);
    }
}
