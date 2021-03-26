package com.example.web.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Jpa - orm mapper /Repository - dao
//<Posts, Long> - posts 프라이머리키의 타입  -Long
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findALlDesc(); //posts 에 있는 레코드들을 내림차순(글번호순)

}
