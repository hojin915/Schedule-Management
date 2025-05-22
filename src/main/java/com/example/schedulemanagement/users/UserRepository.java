package com.example.schedulemanagement.users;

import com.example.schedulemanagement.exception.ValidateFailException;
import com.example.schedulemanagement.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    default User findUserByEmailOrElseThrow(String email){
        return findByEmail(email)
                .orElseThrow(() ->
                        new ValidateFailException(
                                "findUserByEmail, User with email not found " + email,
                                "User not exist or password does not match"
                        )
                );
    }

    default User findByIdOrElseThrow(Long id){
        return findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                "findByIdOrElseThrow(User), User not found id = " + id,
                                "Cannot find User"
                        )
                );
    }
}
