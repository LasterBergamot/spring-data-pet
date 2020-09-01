package com.pet.springdata.domain.user.util;

import com.pet.springdata.domain.user.model.resource.UserResource;
import com.pet.springdata.repository.user.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUtil {

    public static List<UserResource> transformUserListToUserResourceList(List<User> userList) {
        List<UserResource> userResourceList = new ArrayList<>();

        for (User user : userList) {
            userResourceList.add(UserUtil.transformUserToUserResource(user));
        }

        return userResourceList;
    }

    public static UserResource transformUserToUserResource(User user) {
        return UserResource.builder()
                .id(user.getId())
                .name(user.getName())
                .phoneNumbers(user.getPhoneNumbers())
                .build();
    }

    public static User transformUserResourceToUser(UserResource userResource) {
        return User.builder()
                .id(userResource.getId())
                .name(userResource.getName())
                .phoneNumbers(userResource.getPhoneNumbers())
                .build();
    }
}
