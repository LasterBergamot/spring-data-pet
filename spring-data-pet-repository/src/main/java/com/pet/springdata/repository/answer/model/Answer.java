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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

import static com.pet.springdata.repository.util.Constants.COLUMN_NAME_ANSWERED_CORRECTLY;
import static com.pet.springdata.repository.util.Constants.COLUMN_NAME_SELECTED_ANSWER;
import static com.pet.springdata.repository.util.Constants.JOIN_COLUMN_TRIVIA_ID;
import static com.pet.springdata.repository.util.Constants.JOIN_COLUMN_USER_ID;
import static com.pet.springdata.repository.util.Constants.TABLE_NAME_ANSWERS;

@Entity
@Table(name = TABLE_NAME_ANSWERS)
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
    @JoinColumn(name = JOIN_COLUMN_USER_ID)
    @NonNull
    @IndexedEmbedded
    private User user;

    @ManyToOne
    @JoinColumn(name = JOIN_COLUMN_TRIVIA_ID)
    @NonNull
    @IndexedEmbedded
    private Trivia trivia;

    @Column(name = COLUMN_NAME_SELECTED_ANSWER)
    @NonNull
    @Field
    private String selectedAnswer;

    @Column(name = COLUMN_NAME_ANSWERED_CORRECTLY)
    @NonNull
    @Field
    private Boolean answeredCorrectly;
}
