package com.pet.springdata.controller.rest.user;

import com.pet.springdata.repository.user.User;
import com.pet.springdata.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "middleName") String middleName,
                                         @RequestParam(name = "lastName") String lastName, @RequestParam(name = "phoneNumber") String phoneNumber) {
        LOG.info("Saving User with firstName: {}, middleName: {}, lastName: {}, and phoneNumber: {}.", firstName, middleName, lastName, phoneNumber);

        return userService.saveUser(firstName, middleName, lastName, phoneNumber);
    }
}
