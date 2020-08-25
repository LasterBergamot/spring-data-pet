package com.pet.springdata.repository.trivia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriviaRepository extends JpaRepository<Trivia, Short> {}