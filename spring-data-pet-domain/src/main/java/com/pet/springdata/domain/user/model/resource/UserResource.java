package com.pet.springdata.domain.user.model.resource;

import com.pet.springdata.repository.user.model.Name;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Getter
@Builder
public class UserResource {

    @NonNull
    private final Short id;

    @NonNull
    private final Name name;

    @NonNull
    private final Set<String> phoneNumbers;
}
