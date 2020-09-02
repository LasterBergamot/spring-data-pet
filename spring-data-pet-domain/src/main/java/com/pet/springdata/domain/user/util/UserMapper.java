package com.pet.springdata.domain.user.util;

import com.pet.springdata.domain.user.model.resource.UserResource;
import com.pet.springdata.repository.user.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public List<UserResource> transformUserListToUserResourceList(List<User> userList) {
        return userList.stream()
                .map(this::transformUserToUserResource)
                .collect(Collectors.toList());
    }

    public UserResource transformUserToUserResource(User user) {
        return UserResource.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumbers(new HashSet<>(user.getPhoneNumbers()))
                .build();
    }

    public User transformUserResourceToUser(UserResource userResource) {
        return User.builder()
                .id(userResource.getId())
                .name(userResource.getName())
                .phoneNumbers(userResource.getPhoneNumbers())
                .build();
    }
}
