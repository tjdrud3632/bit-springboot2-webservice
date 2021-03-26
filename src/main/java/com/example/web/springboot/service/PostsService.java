package com.example.web.springboot.service;

import com.example.web.springboot.domain.posts.PostsRepository;
import com.example.web.springboot.dto.PostsSaveRequestDto;
import com.example.web.springboot.domain.posts.Posts;
import com.example.web.springboot.dto.PostsListResponseDto;

import com.example.web.springboot.dto.PostsResponseDto;

import com.example.web.springboot.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository; //RequiredArgsConstructor

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){

        return postsRepository.save(requestDto.toEntity()).getId(); //CRUD 작업 수행 (DB작업) -Transactional
    }
    
    @Transactional
    public Long update(Long id , PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id) //수정할 게시글을 테이블에서 얻어냄 /id를 가져와서 posts 객체 넣어줌
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));
                //게시글이 없을 경유 예외 처리

        posts.update(requestDto.getTitle(), requestDto.getContent());
        //update 메소드를 호출해서 파라미터(title, content)를 넘겨준다. - 업데이트를 수행
        return id; //id를 리턴해줌
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다 id="+id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true) // 읽기만 가능
    public PostsResponseDto findById(Long id){ 
        Posts entity = postsRepository.findById(id) //id에 해당하는 entity를 가지고 옴
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다 id="+id ));
                // 없을 경우 예외처리
        return  new PostsResponseDto(entity); //postResponseDto에 entity를 넘겨줌
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return  postsRepository.findALlDesc().stream() //스트림을 map을 통해 dto를 변환
                // 클래스::new 람다식 생성자를 참조한다 
                .map(PostsListResponseDto::new) //map으로 래핑시켜줌
                .collect(Collectors.toList()); //그리고 list로 모아줌
  }
}
