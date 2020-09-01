package com.pet.springdata.repository.trivia.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "trivia")
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Builder
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Indexed
public class Trivia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Short id;

    @NonNull
    @Field
    private String category;

    @NonNull
    @Field
    private String type;

    @NonNull
    @Field
    private String difficulty;

    @NonNull
    @Field
    private String question;

    @Column(name = "correct_answer")
    @NonNull
    @Field
    private String correctAnswer;

    @Type(type = "list-array")
    @Column(name = "incorrect_answers")
    @NonNull
    @Field
    private List<String> incorrectAnswers;
}
