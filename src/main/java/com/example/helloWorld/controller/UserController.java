package com.example.helloWorld.controller;

import com.example.helloWorld.Service.UserService;
import com.example.helloWorld.dto.DeleteReviewRequestDto;
import com.example.helloWorld.dto.UpdateReviewRequestDto;
import com.example.helloWorld.dto.CreateUserDto;
import com.example.helloWorld.dto.UpdateUserDto;
import com.example.helloWorld.exception.MovieNotFoundException;
import com.example.helloWorld.exception.ReviewNotFoundException;
import com.example.helloWorld.exception.UserNotFoundException;
import com.example.helloWorld.model.Movie;
import com.example.helloWorld.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody final CreateUserDto userDto){
        log.info("Request received to create a user : {}", userDto);
        userService.createUser(userDto);
        return ResponseEntity.ok("User created successfully with username: " + userDto.getUserName());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getAUser(@PathVariable("userId") final int userId) throws UserNotFoundException {
        log.info("Request received to get a user with id: {}", userId);
        try{
            return ResponseEntity.ok(userService.
                    getAUser(userId));
        } catch (UserNotFoundException ex){
            return ResponseEntity.status(404).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        log.info("Request received to get all users");
        return ResponseEntity.ok(userService.getAllUser());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") final int userId) {
        log.info("Request received to delete a user with user id: {}", userId);
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("user Successfully deleted ");
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/{id}") //PATCH localhost:8080/reviews/3
    public ResponseEntity<User> updateUser(@PathVariable("id") final int userId, @RequestBody final UpdateUserDto updateUserDto) {
        log.info("Request received to update a user with user id: {}", userId);
        try {
            final User updatedUser = userService.updateUser(userId, updateUserDto);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //delete, update, get all users

}


//1. Create User
//2. Get A User
//3. Delete a User

//When I call get a user, I should find the reviews he has posted

