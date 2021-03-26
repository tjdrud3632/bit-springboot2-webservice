package com.example.web.springboot.web;

import com.example.web.springboot.service.PostsService;
//import com.example.web.springboot.web.dto.PostsListResponseDto;

import com.example.web.springboot.dto.PostsResponseDto;
import com.example.web.springboot.dto.PostsSaveRequestDto;
import com.example.web.springboot.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//request를 받아서 처리해주는 컨트롤러

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    //객체를 주입받을 때 -3가지 방법 1)생성자 메소드 2)Autowired 3)setter메소드
    private final PostsService postsService; //@RequiredArgsConstructor 사용해서 생성자메소드 사용


    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
        //서비스를 호출해서 디비 작업을 하도록
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
       //수정할 내용이 담길 DTO - PostsUpdateRequestDto
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")  //json형식으로 데이터가 출력됨.
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}


