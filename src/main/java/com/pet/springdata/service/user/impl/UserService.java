package com.pet.springdata.service.user.impl;

import com.pet.springdata.repository.user.Name;
import com.pet.springdata.repository.user.User;
import com.pet.springdata.repository.user.UserRepository;
import com.pet.springdata.service.user.TriviaUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements TriviaUserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> saveUser(String firstName, String middleName, String lastName, String phoneNumber) {
        LOG.info("Saving User with firstName: {}, middleName: {}, lastName: {}, and phoneNumber: {}.", firstName, middleName, lastName, phoneNumber);
        Name name = new Name(firstName, middleName, lastName);
        Set<String> phoneNumbers = Set.of(phoneNumber);
        User user = new User(name, phoneNumbers);

        return ResponseEntity.ok(userRepository.save(user));
    }
}
