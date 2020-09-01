package com.pet.springdata.domain.user.impl;

import com.pet.springdata.domain.user.UserService;
import com.pet.springdata.domain.user.model.resource.UserResource;
import com.pet.springdata.domain.user.util.UserUtil;
import com.pet.springdata.repository.user.UserRepository;
import com.pet.springdata.repository.user.model.Name;
import com.pet.springdata.repository.user.model.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static com.pet.springdata.domain.util.Constants.CACHE_NAME_USER_CACHE;
import static com.pet.springdata.domain.util.Constants.DEFAULT_FIRST_NAME;
import static com.pet.springdata.domain.util.Constants.DEFAULT_LAST_NAME;
import static com.pet.springdata.domain.util.Constants.DEFAULT_MIDDLE_NAME;
import static com.pet.springdata.domain.util.Constants.DEFAULT_PHONE_NUMBER;

@Service
@RequiredArgsConstructor
@Slf4j
@Cacheable(value = CACHE_NAME_USER_CACHE)
public class TriviaUserService implements UserService {

    @NonNull
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseEntity<UserResource> saveUser(String firstName, String middleName, String lastName, String phoneNumber) {
        log.info("Saving User with firstName: {}, middleName: {}, lastName: {}, and phoneNumber: {}.", firstName, middleName, lastName, phoneNumber);
        Name name = new Name(firstName, middleName, lastName);
        Set<String> phoneNumbers = Set.of(phoneNumber);
        User user = userRepository.save(new User(name, phoneNumbers));

        return ResponseEntity.ok(UserUtil.transformUserToUserResource(user));
    }

    @Override
    @Transactional
    public UserResource findById(short id) {
        log.info("Finding User with id: {}", id);
        User user = userRepository
                .findById(id)
                .orElse(getDefaultUser());

        return UserUtil.transformUserToUserResource(user);
    }

    @Override
    @Transactional
    public List<UserResource> findAllUser() {
        log.info("Finding all User.");
        return UserUtil.transformUserListToUserResourceList(userRepository.findAll());
    }

    private User getDefaultUser() {
        return new User(new Name(DEFAULT_FIRST_NAME, DEFAULT_MIDDLE_NAME, DEFAULT_LAST_NAME), Set.of(DEFAULT_PHONE_NUMBER));
    }
}
