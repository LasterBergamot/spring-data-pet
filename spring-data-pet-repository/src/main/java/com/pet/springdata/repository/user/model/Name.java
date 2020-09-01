package com.pet.springdata.repository.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Name implements Serializable {

    @NonNull
    private String firstName;

    @NonNull
    private String middleName;

    @NonNull
    private String lastName;
}
