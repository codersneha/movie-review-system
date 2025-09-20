package com.example.helloWorld.Service;

import com.example.helloWorld.dto.CreateUserDto;
import com.example.helloWorld.dto.UpdateUserDto;
import com.example.helloWorld.exception.UserNotFoundException;
import com.example.helloWorld.model.User;
import com.example.helloWorld.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private static int userId = 1;
    private final IUserRepository userRepository; // loose coupling

    public void createUser(final CreateUserDto createUserDto) {
        final User user = User.builder()
                .userName(createUserDto.getUserName())
                .id(userId++)
                .firstName(createUserDto.getFirstName())
                .lastname(createUserDto.getLastName())
                .email(createUserDto.getEmail())
                .build();
        userRepository.save(user);

    }

    public List<User> getAllUser() {
        return userRepository.getAll();
    }

    public User getAUser(int userId) throws UserNotFoundException {
        return userRepository.get(userId);
    }

    public void deleteUser(int userId) throws UserNotFoundException  {
        userRepository.delete(userId);
    }

    public User updateUser(int userId, UpdateUserDto updateUserDto) throws UserNotFoundException {
        return userRepository.update(userId,updateUserDto.getFirstName(), updateUserDto.getLastName(), updateUserDto.getEmail());
    }


}
