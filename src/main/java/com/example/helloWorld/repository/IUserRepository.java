package com.example.helloWorld.repository;

import com.example.helloWorld.dto.UpdateUserDto;
import com.example.helloWorld.exception.ReviewNotFoundException;
import com.example.helloWorld.exception.UserNotFoundException;
import com.example.helloWorld.model.Review;
import com.example.helloWorld.model.User;

import java.util.List;

public interface IUserRepository {

    void save(User user);
    User get(int id) throws UserNotFoundException;
    List<User> getAll();
    User update(int id, String firstName, String lastName, String email) throws UserNotFoundException;
    void delete(int userId) throws UserNotFoundException;
    void addReview(int userId, Review review) throws UserNotFoundException;
    void deleteReview(int userId, int reviewId) throws UserNotFoundException, ReviewNotFoundException;
}
