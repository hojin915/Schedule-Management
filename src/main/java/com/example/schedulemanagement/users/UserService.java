package com.example.schedulemanagement.users;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserResponseDto save(String name, String email, String password) {
        User user = new User(name, email, password);

        User savedUser = userRepository.save(user);

        return new UserResponseDto(savedUser);
    }

    public UserResponseDto findUserById(Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        return new UserResponseDto(user);
    }

    public List<UserResponseDto> findAllUsers() {
        List<UserResponseDto> responseDtoList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for(User user : userList) {
            responseDtoList.add(new UserResponseDto(user));
        }
        return responseDtoList;
    }

    @Transactional
    public UserResponseDto updateUser(Long userId, String name, String email, String password) {
        User user = userRepository.findByIdOrElseThrow(userId);

        if(!user.getUsername().equals(name) || !user.getPassword().equals(password)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "wrong name or password");
        }

        user.updateEmail(email);

        return new UserResponseDto(user);
    }

    @Transactional
    public void delete(Long userId) {
        User findUser = userRepository.findByIdOrElseThrow(userId);
        userRepository.delete(findUser);
    }
}
