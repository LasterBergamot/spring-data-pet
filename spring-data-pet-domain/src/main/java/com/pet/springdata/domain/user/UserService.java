package com.pet.springdata.domain.user;

import com.pet.springdata.repository.user.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<User> saveUser(String firstName, String middleName, String lastName, String phoneNumber);

    User findById(short id);

    List<User> findAllUser();
}
