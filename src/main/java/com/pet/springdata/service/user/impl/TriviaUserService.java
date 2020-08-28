package com.pet.springdata.service.user.impl;

import com.pet.springdata.repository.user.model.Name;
import com.pet.springdata.repository.user.model.User;
import com.pet.springdata.repository.user.UserRepository;
import com.pet.springdata.service.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
@CacheConfig(cacheNames = "EventConfig")
public class TriviaUserService implements UserService {

    @NonNull
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<User> saveUser(String firstName, String middleName, String lastName, String phoneNumber) {
        log.info("Saving User with firstName: {}, middleName: {}, lastName: {}, and phoneNumber: {}.", firstName, middleName, lastName, phoneNumber);
        Name name = new Name(firstName, middleName, lastName);
        Set<String> phoneNumbers = Set.of(phoneNumber);
        User user = new User(name, phoneNumbers);

        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public User findById(short id) {
        log.info("Finding User with id: {}", id);
        return userRepository
                .findById(id)
                .orElse(getDefaultUser());
    }

    private User getDefaultUser() {
        return new User(new Name("defaultFirstName", "defaultMiddleName", "defaultLastName"), Set.of("06808888888"));
    }
}
