package com.example.web.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest //자동으로 in memory database 사용이 가능 - 테이블 생성등 작업이 안에서 가능함
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void insertNreadArticle() {

        String title = "테스트 게시글";
        String content ="테스트 본문";
 
        //jpaRepository - CURD 메소드가 다 들어있다 (상속을 받아서 메소드 사용이 가능하다)
        postsRepository.save(Posts.builder() // Builder design pattern 
                .title(title)
                .content(content)
                .author("aaa@gmail.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts =postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title); //같은지 콘솔에 출력
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void registBaseTimeEntity(){

        //given
        LocalDateTime now = LocalDateTime.of(2021, 3,25,14,40,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        //post 객체 생성 후 save

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0); //첫번째 데이타를 꺼내옴 (posts는 basetimeentity를 상속받아서 생성시간과 수정시간을 가져올 수 있음)

        System.out.println(">>>>>>>>>>>>>> createDate="+ posts.getCreateDate()+", modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreateDate()).isAfter(now);//now보다 후인지 체크함
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
