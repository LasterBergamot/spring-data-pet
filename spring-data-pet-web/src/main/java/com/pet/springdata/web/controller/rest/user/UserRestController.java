package com.pet.springdata.web.controller.rest.user;

import com.pet.springdata.domain.user.UserService;
import com.pet.springdata.domain.user.model.resource.UserResource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_ALL;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_SAVE_USER;
import static com.pet.springdata.web.util.Constants.REQUEST_MAPPING_USER;
import static com.pet.springdata.web.util.Constants.REQUEST_PARAM_FIRST_NAME;
import static com.pet.springdata.web.util.Constants.REQUEST_PARAM_LAST_NAME;
import static com.pet.springdata.web.util.Constants.REQUEST_PARAM_MIDDLE_NAME;
import static com.pet.springdata.web.util.Constants.REQUEST_PARAM_PHONE_NUMBER;

@RestController
@RequestMapping(REQUEST_MAPPING_USER)
@RequiredArgsConstructor
@Slf4j
public class UserRestController {

    @NonNull
    private final UserService userService;

    @PostMapping(REQUEST_MAPPING_SAVE_USER)
    public ResponseEntity<UserResource> saveUser(@RequestParam(name = REQUEST_PARAM_FIRST_NAME) String firstName, @RequestParam(name = REQUEST_PARAM_MIDDLE_NAME) String middleName,
                                                 @RequestParam(name = REQUEST_PARAM_LAST_NAME) String lastName, @RequestParam(name = REQUEST_PARAM_PHONE_NUMBER) String phoneNumber) {
        log.info("Saving User with firstName: {}, middleName: {}, lastName: {}, and phoneNumber: {}.", firstName, middleName, lastName, phoneNumber);

        return userService.saveUser(firstName, middleName, lastName, phoneNumber);
    }

    @GetMapping(REQUEST_MAPPING_ALL)
    public List<UserResource> findAllUser() {
        log.info("Finding all User.");
        return userService.findAllUser();
    }
}
