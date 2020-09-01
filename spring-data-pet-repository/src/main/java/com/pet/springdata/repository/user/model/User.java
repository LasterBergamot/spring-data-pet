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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
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
            @AttributeOverride(name = "firstName", column = @Column(name = "first_name")),
            @AttributeOverride(name = "middleName", column = @Column(name = "middle_name")),
            @AttributeOverride(name = "lastName", column = @Column(name = "last_name"))
    })
    @NonNull
    @IndexedEmbedded
    private Name name;

    @ElementCollection
    @CollectionTable(name = "phone_numbers", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "phone_number")
    @NonNull
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @IndexedEmbedded
    private Set<String> phoneNumbers;
}
