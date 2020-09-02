package com.pet.springdata.domain.user;

import com.pet.springdata.domain.user.model.resource.UserResource;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<UserResource> saveUser(String firstName, String middleName, String lastName, String phoneNumber);

    UserResource findById(short id);

    List<UserResource> findAllUser();
}
