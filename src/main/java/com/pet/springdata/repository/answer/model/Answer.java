package com.pet.springdata.repository.answer.model;

import com.pet.springdata.repository.trivia.model.Trivia;
import com.pet.springdata.repository.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "answers")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Indexed
public class Answer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Short id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    @IndexedEmbedded
    private User user;

    @ManyToOne
    @JoinColumn(name = "trivia_id")
    @NonNull
    @IndexedEmbedded
    private Trivia trivia;

    @Column(name = "selected_answer")
    @NonNull
    @Field
    private String selectedAnswer;

    @Column(name = "answered_correctly")
    @NonNull
    @Field
    private Boolean answeredCorrectly;
}
