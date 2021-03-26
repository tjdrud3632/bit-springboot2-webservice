package com.example.web.springboot.web;

import com.example.web.springboot.dto.PostsSaveRequestDto;
import com.example.web.springboot.dto.PostsUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.web.springboot.domain.posts.Posts;
import com.example.web.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.security.test.web.servlet.setup.securityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
//jpa 기능 사용가능 - SpringBootTest /jpa 기능 test를 함께하기 위함
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; //jpa 기능 test를 함께하기 위함

    @Autowired
    private PostsRepository postsRepository; 

    @Autowired
    private WebApplicationContext context; 

    private MockMvc mvc;

     //port Num 부여 - 테이블 생성 - 삽입 - 삭제(after)
    @Before //테스트가 시작하기 전에 실행되는 메소드
    public void setup(){
        mvc = MockMvcBuilders //mockMvc객체 빌드
                .webAppContextSetup(context)
                //.apply(springSecurity())
                .build();
    }

    @After //테스트가 끝난 후에 실행되는 메소드
    public void tearDown() throws Exception{
        postsRepository.deleteAll(); //안에 있는 데이터 삭제
    }

    @Test
    //@WithMockUser(roles="USER")
    public void registerPosts() throws Exception{
        //given
        String title ="title";
        String content ="content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder() //dto객체 생성
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url ="http://localhost:"+ port + "/api/v1/posts";
        //url을 인위적으로 만들어줌 (port - random port사용)

        //when
        mvc.perform(post(url) //post 방식
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto))) 
                .andExpect(status().isOk());

        //then
        List<Posts> all = postsRepository.findAll();// 집어넣은 데이터를 꺼냄
        assertThat(all.get(0).getTitle()).isEqualTo(title); //콘솔에 출력해줌
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
   // @WithMockUser(roles="USER")
    public void updatePosts() throws  Exception{

        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url ="http://localhost:"+ port + "/api/v1/posts/"+updateId;

        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }

}
