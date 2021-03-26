package com.example.web.springboot.dto;

import com.example.web.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class PostsSaveRequestDto { //화면과 관련된 데이타 / Posts는 테이블과 연관된 클래스

    private  String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;

    }
    public Posts toEntity(){ //Entity를 만들어내는 메소드
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
