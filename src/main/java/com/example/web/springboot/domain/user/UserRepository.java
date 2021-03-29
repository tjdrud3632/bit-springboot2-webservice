package com.example.web.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); //이메일을 파라미터로 받아서 유저를 찾음
    //optional - yes or no 판단 /이메일로 유저가 기존회원인지 아닌지를 체크

}
