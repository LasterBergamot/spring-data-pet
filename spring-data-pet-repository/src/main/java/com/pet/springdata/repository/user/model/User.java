package com.pet.springdata.repository.user.model;

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
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

import static com.pet.springdata.repository.util.Constants.ATTRIBUTE_OVERRIDE_NAME_FIRST_NAME;
import static com.pet.springdata.repository.util.Constants.ATTRIBUTE_OVERRIDE_NAME_LAST_NAME;
import static com.pet.springdata.repository.util.Constants.ATTRIBUTE_OVERRIDE_NAME_MIDDLE_NAME;
import static com.pet.springdata.repository.util.Constants.COLLECTION_TABLE_NAME_PHONE_NUMBERS;
import static com.pet.springdata.repository.util.Constants.COLUMN_NAME_FIRST_NAME;
import static com.pet.springdata.repository.util.Constants.COLUMN_NAME_LAST_NAME;
import static com.pet.springdata.repository.util.Constants.COLUMN_NAME_MIDDLE_NAME;
import static com.pet.springdata.repository.util.Constants.COLUMN_NAME_PHONE_NUMBER;
import static com.pet.springdata.repository.util.Constants.JOIN_COLUMN_USER_ID;
import static com.pet.springdata.repository.util.Constants.TABLE_NAME_USERS;

@Entity
@Table(name = TABLE_NAME_USERS)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Indexed
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Short id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = ATTRIBUTE_OVERRIDE_NAME_FIRST_NAME, column = @Column(name = COLUMN_NAME_FIRST_NAME)),
            @AttributeOverride(name = ATTRIBUTE_OVERRIDE_NAME_MIDDLE_NAME, column = @Column(name = COLUMN_NAME_MIDDLE_NAME)),
            @AttributeOverride(name = ATTRIBUTE_OVERRIDE_NAME_LAST_NAME, column = @Column(name = COLUMN_NAME_LAST_NAME))
    })
    @NonNull
    @IndexedEmbedded
    private Name name;

    @ElementCollection
    @CollectionTable(name = COLLECTION_TABLE_NAME_PHONE_NUMBERS, joinColumns = @JoinColumn(name = JOIN_COLUMN_USER_ID))
    @Column(name = COLUMN_NAME_PHONE_NUMBER)
    @NonNull
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @IndexedEmbedded
    private Set<String> phoneNumbers;
}
