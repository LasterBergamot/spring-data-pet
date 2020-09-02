package com.pet.springdata.domain.user.model.resource;

import com.pet.springdata.repository.user.model.Name;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Builder
public class UserResource implements Serializable {

    @ApiModelProperty(
            value = "Id of the user stored in the database",
            name = "id",
            dataType = "Short",
            example = "1"
    )
    @NonNull
    private final Short id;

    @ApiModelProperty(
            value = "Name of the user",
            name = "name",
            dataType = "Name",
            example = "Andrew Henry Mourdough"
    )
    @NonNull
    private final Name name;

    @ApiModelProperty(
            value = "All phone numbers of the user",
            name = "phoneNumbers",
            dataType = "Set<String>",
            example = "+36307654129, +36709123548"
    )
    @NonNull
    private final Set<String> phoneNumbers;
}
