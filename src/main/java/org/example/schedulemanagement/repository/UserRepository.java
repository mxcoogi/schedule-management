package org.example.schedulemanagement.repository;

import org.example.schedulemanagement.entity.User;
import org.example.schedulemanagement.global.ErrorCode;
import org.example.schedulemanagement.global.exception.CustomeException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    default User findUserByEmailOrElseThrow(String email){
        return findUserByEmail(email).orElseThrow(()-> new CustomeException(ErrorCode.USER_NOT_FOUND));
    }


    Optional<User> findUserById(Long id);
    default User findUserByIdOrElseThrow(Long id){
        return findUserById(id).orElseThrow(()->new CustomeException(ErrorCode.USER_NOT_FOUND));
    }
}
