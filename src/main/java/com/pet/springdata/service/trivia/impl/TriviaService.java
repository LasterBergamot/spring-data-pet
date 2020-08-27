package com.pet.springdata.service.trivia.impl;

import com.pet.springdata.model.trivia.TriviaDTO;
import com.pet.springdata.repository.trivia.Trivia;
import com.pet.springdata.repository.trivia.TriviaRepository;
import com.pet.springdata.service.trivia.ITriviaService;
import com.pet.springdata.util.trivia.TriviaUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TriviaService implements ITriviaService {

    @NonNull
    private final TriviaRepository triviaRepository;

    @Override
    public ResponseEntity<List<Trivia>> saveTrivia(List<TriviaDTO> triviaDTOList) {
        log.info("Saving {} Trivia.", triviaDTOList.size());
        List<Trivia> triviaList = triviaRepository.saveAll(TriviaUtil.transformTriviaDTOListToTriviaList(triviaDTOList));

        return ResponseEntity.ok(triviaList);
    }

    //TODO: for larger databases this approach is not suitable, do it in a different way
    @Override
    public List<Trivia> findTrivia(int numberOfTrivia) {
        log.info("Getting {} Trivia from the database.", numberOfTrivia);
        List<Trivia> triviaList = triviaRepository.findAll();
        Collections.shuffle(triviaList);

        return triviaList.subList(0, numberOfTrivia);
    }
}
