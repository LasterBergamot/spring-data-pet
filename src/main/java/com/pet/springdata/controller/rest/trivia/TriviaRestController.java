package com.pet.springdata.controller.rest.trivia;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.Trivia;
import com.pet.springdata.service.facade.OpenTriviaDatabaseFacade;
import com.pet.springdata.service.trivia.IOpenTriviaDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trivia")
public class TriviaRestController {

    private static final Logger LOG = LoggerFactory.getLogger(TriviaRestController.class);

    private final OpenTriviaDatabaseFacade openTriviaDatabaseFacade;

    @Autowired
    public TriviaRestController(OpenTriviaDatabaseFacade openTriviaDatabaseFacade) {
        this.openTriviaDatabaseFacade = openTriviaDatabaseFacade;
    }

    @GetMapping("/getTrivia")
    public List<TriviaDTO> getTrivia(@RequestParam(name = "numberOfTrivia") int numberOfTrivia) {
        LOG.info("Getting {} Trivia.", numberOfTrivia);
        return openTriviaDatabaseFacade.getTrivia(numberOfTrivia);
    }

    //TODO: do not send back entity
    @GetMapping("/saveTrivia")
    public ResponseEntity<List<Trivia>> saveTrivia(@RequestParam(name = "numberOfTrivia") int numberOfTrivia) {
        LOG.info("Saving {} Trivia.", numberOfTrivia);

        return openTriviaDatabaseFacade.saveTrivia(numberOfTrivia);
    }
}
