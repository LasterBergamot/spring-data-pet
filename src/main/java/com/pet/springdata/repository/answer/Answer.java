package com.pet.springdata.repository.answer;

import com.pet.springdata.repository.trivia.Trivia;
import com.pet.springdata.repository.user.User;

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
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "trivia_id")
    private Trivia trivia;

    @Column(name = "selected_answer")
    private String selectedAnswer;

    @Column(name = "answered_correctly")
    private Boolean answeredCorrectly;

    public Answer() {}

    public Answer(User user, Trivia trivia, String selectedAnswer, Boolean answeredCorrectly) {
        this.user = user;
        this.trivia = trivia;
        this.selectedAnswer = selectedAnswer;
        this.answeredCorrectly = answeredCorrectly;
    }

    public Short getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trivia getTrivia() {
        return trivia;
    }

    public void setTrivia(Trivia trivia) {
        this.trivia = trivia;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public Boolean getAnsweredCorrectly() {
        return answeredCorrectly;
    }

    public void setAnsweredCorrectly(Boolean answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }
}
