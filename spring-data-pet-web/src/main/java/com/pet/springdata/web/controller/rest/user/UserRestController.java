package com.pet.springdata.web.controller.rest.user;

import com.pet.springdata.repository.user.model.User;
import com.pet.springdata.domain.user.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserRestController {

    @NonNull
    private final UserService userService;

    @GetMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "middleName") String middleName,
                                         @RequestParam(name = "lastName") String lastName, @RequestParam(name = "phoneNumber") String phoneNumber) {
        log.info("Saving User with firstName: {}, middleName: {}, lastName: {}, and phoneNumber: {}.", firstName, middleName, lastName, phoneNumber);

        return userService.saveUser(firstName, middleName, lastName, phoneNumber);
    }

    @GetMapping("/all")
    public List<User> findAllUser() {
        log.info("Finding all User.");
        return userService.findAllUser();
    }
}
