package com.pet.springdata.repository.answer.model;

import com.pet.springdata.repository.trivia.model.Trivia;
import com.pet.springdata.repository.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "answers")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Short id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "trivia_id")
    @NonNull
    private Trivia trivia;

    @Column(name = "selected_answer")
    @NonNull
    private String selectedAnswer;

    @Column(name = "answered_correctly")
    @NonNull
    private Boolean answeredCorrectly;
}
