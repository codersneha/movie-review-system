package com.example.helloWorld.repository;

import com.example.helloWorld.dto.UpdateUserDto;
import com.example.helloWorld.exception.MovieNotFoundException;
import com.example.helloWorld.exception.ReviewNotFoundException;
import com.example.helloWorld.exception.UserNotFoundException;
import com.example.helloWorld.model.Movie;
import com.example.helloWorld.model.Review;
import com.example.helloWorld.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class InMemoryUserRepository implements IUserRepository {

    private final List<User> userList = new ArrayList<>();


    @Override
    public void save(User user) {
        userList.add(user);
        log.info("user {} created:", user);
    }

    @Override
    public User get(int id) throws UserNotFoundException {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFoundException(String.format("User with %s not found", id));
    }

    @Override
    public List<User> getAll() {
        return userList;
    }

    @Override
    public User update(int id, String firstName, String lastName, String email) throws UserNotFoundException {
        final User existingUser = get(id);
        final User updatedUser = User.builder()
                .id(existingUser.getId())
                .userName(existingUser.getUserName())
                .firstName(firstName == null ? existingUser.getFirstName() : firstName)
                .lastname(lastName == null ? existingUser.getLastname() : lastName)
                .email(email == null ? existingUser.getEmail() : email)
                .build();
        delete(id);
        save(updatedUser);
        return updatedUser;
    }

    @Override
    public void delete(int userId) throws UserNotFoundException {
        for (User user : userList) {
            if (user.getId()==(userId)) {
                userList.remove(user);
                return;
            }
            throw new UserNotFoundException("User with userName " + userId + " not found");
        }
    }

    @Override
    public void addReview(int userId, Review review) throws UserNotFoundException {
        final User user = get(userId);
        user.addReview(review);
    }

    @Override
    public void deleteReview(int userId, int reviewId) throws UserNotFoundException, ReviewNotFoundException {
        final User user = get(userId);
        for (Review review : user.getReviews()) {
            if (review.getId() == reviewId && review.getUserId() == userId) {
                user.deleteReview(review);
                return;
            }
        }
        throw new ReviewNotFoundException(String.format("review id %s not found", reviewId));
    }

}


