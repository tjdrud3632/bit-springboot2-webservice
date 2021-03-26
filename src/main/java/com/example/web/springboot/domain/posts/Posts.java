package com.example.web.springboot.domain.posts;

import com.example.web.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter //getter생성
@NoArgsConstructor  //파라미터가 없는 기본생성자를 생성해줌
@Entity //테이블과 매핑될 클래스
public class Posts extends BaseTimeEntity {
    //테이블 설계 -jpa /h2 인메모리 - 영구적 사용x 테스트용 o
    //Posts라는 테이블을 생성해줌 @id @column 테이블의 스키마

    @Id //프라이머리 키 (보통 id를 프라이머리키로 씀)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프라이머리키 생성규칙
    //GenerationType.IDENTIT - auto Increment (레코드 추가시, 특정칼럼(id)이 자동으로 증가 되도록)
    private Long id;

    @Column(length = 500, nullable = false) //칼럼에 해당되는 데이터 요소 (500글자 까지 가능) / null값이 들어가면 안됨 (Not Null)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false) //columnDefinition - text으로 들어가도록 (사이즈가 탄력적으로 늘어나도록) / Not Null
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){ //생성자 메소드
        this.title = title;
        this.content = content;
        this.author = author;

    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
   }

}
