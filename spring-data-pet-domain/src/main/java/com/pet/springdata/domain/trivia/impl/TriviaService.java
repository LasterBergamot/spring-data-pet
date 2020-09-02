package com.pet.springdata.domain.trivia.impl;

import com.pet.springdata.domain.trivia.ITriviaService;
import com.pet.springdata.domain.trivia.model.OpenTriviaDatabaseResult;
import com.pet.springdata.domain.trivia.model.resource.TriviaResource;
import com.pet.springdata.domain.trivia.util.OpenTriviaDatabaseMapper;
import com.pet.springdata.repository.trivia.TriviaRepository;
import com.pet.springdata.repository.trivia.TriviaRepositoryCustom;
import com.pet.springdata.repository.trivia.model.Trivia;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.pet.springdata.domain.util.Constants.CACHE_NAME_TRIVIA_CACHE;

@Service
@RequiredArgsConstructor
@Slf4j
@Cacheable(value = CACHE_NAME_TRIVIA_CACHE)
public class TriviaService implements ITriviaService {

    @NonNull
    private final TriviaRepository triviaRepository;

    @NonNull
    private final TriviaRepositoryCustom triviaRepositoryCustom;

    @NonNull
    private final OpenTriviaDatabaseMapper openTriviaDatabaseMapper;

    @Override
    @Transactional
    public ResponseEntity<List<TriviaResource>> saveTrivia(List<OpenTriviaDatabaseResult> openTriviaDatabaseResultList) {
        log.info("Saving {} Trivia.", openTriviaDatabaseResultList.size());
        List<Trivia> triviaList = triviaRepository.saveAll(openTriviaDatabaseMapper.transformOpenTriviaDatabaseResultListToTriviaList(openTriviaDatabaseResultList));

        return ResponseEntity.ok(openTriviaDatabaseMapper.transformTriviaListToTriviaResourceList(triviaList));
    }

    //TODO: for larger databases this approach is not suitable, do it in a different way
    @Override
    @Transactional
    public List<TriviaResource> findTrivia(int numberOfTrivia) {
        log.info("Getting {} Trivia from the database.", numberOfTrivia);
        List<Trivia> triviaList = triviaRepository.findAll();
        Collections.shuffle(triviaList);

        return openTriviaDatabaseMapper.transformTriviaListToTriviaResourceList(triviaList.subList(0, numberOfTrivia));
    }

    @Override
    @Transactional
    public List<TriviaResource> findAllTrivia() {
        log.info("Finding all Trivia.");
        return openTriviaDatabaseMapper.transformTriviaListToTriviaResourceList(triviaRepository.findAll());
    }

    @Override
    @Transactional
    public ResponseEntity<List<TriviaResource>> findTriviaByCategoryTypeAndDifficulty(String category, String type, String difficulty) {
        log.info("Getting Trivia with category: {}, type: {}, and difficulty: {}.", category, type, difficulty);
        List<Trivia> triviaList = triviaRepositoryCustom.findTriviaByCategoryTypeAndDifficulty(category, type, difficulty);

        return ResponseEntity.ok(openTriviaDatabaseMapper.transformTriviaListToTriviaResourceList(triviaList));
    }
}
