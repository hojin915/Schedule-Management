package com.example.schedulemanagement.users;

import com.example.schedulemanagement.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @RequestBody UserLoginRequestDto dto,
            HttpServletRequest request
    ) {
       UserResponseDto responseDto = userService.login(dto.getEmail(), dto.getPassword());

       HttpSession session = request.getSession();

       session.setAttribute(Const.LOGIN_USER, responseDto);

       return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> save(@RequestBody UserSignUpRequestDto dto) {
        UserResponseDto userResponseDto = userService.save(
                dto.getName(),
                dto.getEmail(),
                dto.getPassword()
        );

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long userId){
        UserResponseDto userResponseDto = userService.findUserById(userId);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers(){
        List<UserResponseDto> responseDtoList = userService.findAllUsers();
        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequestDto dto
    ){
        UserResponseDto userResponseDto = userService.updateUser(
                userId,
                dto.getName(),
                dto.getEmail(),
                dto.getPassword()
        );
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
