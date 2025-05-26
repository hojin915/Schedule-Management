package com.example.schedulemanagement.users;

import com.example.schedulemanagement.config.PasswordEncoder;
import com.example.schedulemanagement.exception.ValidateFailException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto login(String email, String password) {
        User user = userRepository.findUserByEmailOrElseThrow(email);
        // 입력한 비밀번호와 암호화된 DB 비밀번호 비교
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new ValidateFailException(
                    "login, Password mismatch",
                    "User not exist or password does not match"
            );
        }
        return new UserResponseDto(user);
    }

    public UserResponseDto save(String name, String email, String password) {
        // DB에 비밀번호 저장할 때 암호화
        password = passwordEncoder.encode(password);
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

        // 이름, 비밀번호 재확인
        if(!user.getUsername().equals(name) || !passwordEncoder.matches(password, user.getPassword())){
            throw new ValidateFailException(
                    "updateUser, recheck username or password",
                    "User not exist or password does not match"
            );
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
