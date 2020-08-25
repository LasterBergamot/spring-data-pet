package com.pet.springdata.service.user;

import com.pet.springdata.repository.user.User;
import org.springframework.http.ResponseEntity;

public interface TriviaUserService {
    ResponseEntity<User> saveUser(String firstName, String middleName, String lastName, String phoneNumber);
}
