package org.example.schedulemanagement.repository;

import org.example.schedulemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
    default User findUserByEmailOrElseThrow(String email){
        return findUserByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다"+email));
    }


    Optional<User> findUserById(Long id);
    default User findUserByIdOrElseThrow(Long id){
        return findUserById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다"+id));
    }
}
