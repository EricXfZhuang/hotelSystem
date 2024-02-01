package com.org.hotelSystem.controller;

import com.org.hotelSystem.model.User;
import com.org.hotelSystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserController")
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    /**
     * register a user account with all the user info
     * @param user
     * @return
     */
    @Operation(summary = "register a user account with all the user info", description = "register a user account with all the user info")
    @PostMapping(path = "/user/register")
    public boolean register(@RequestBody User user){
        try{
            LOGGER.info("Registering user {}.", user.getUserName());
            userService.register(user);
        }catch (Exception e){
            LOGGER.error("Registering user {} failed: ", user.getUserName(), e);
            return false;
        }
        return true;
    }

    /**
     * Search the user with the userName
     * @param userName
     * @return
     */
    @Operation(summary = "Search the user with the userName", description = "Search the user with the userName")
    @GetMapping(path = "/user/getUser")
    public User getUser(String userName){
        LOGGER.info("Fetching user info");
        return userService.getUser(userName);
    }
}
