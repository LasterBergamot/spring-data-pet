package com.pet.springdata.controller.rest.trivia;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.Trivia;
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
    public ResponseEntity<List<TriviaDTO>> getTrivia(@RequestParam(name = "numberOfTrivia") int numberOfTrivia) {
        log.info("Getting {} Trivia.", numberOfTrivia);
        return openTriviaDatabaseFacade.getTrivia(numberOfTrivia);
    }

    //TODO: do not send back entity
    @GetMapping("/saveTrivia")
    public ResponseEntity<List<Trivia>> saveTrivia(@RequestParam(name = "numberOfTrivia") int numberOfTrivia) {
        log.info("Saving {} Trivia.", numberOfTrivia);

        return openTriviaDatabaseFacade.saveTrivia(numberOfTrivia);
    }
}
