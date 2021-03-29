package com.example.web.springboot.web;

//import com.example.web.springboot.config.auth.LoginUser;
import com.example.web.springboot.config.auth.dto.SessionUser;
import com.example.web.springboot.service.PostsService;
import com.example.web.springboot.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

 /*   @GetMapping("/")
    public String index(){
        //이름이 index이고 확장자가 mustache인
        return "index";      //src/main/resource/templates/*.mustache
    }*/

    @GetMapping("/")
    public String index(Model model, /*@LoginUser*/ SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save"; //posts-save.mustache
    }


    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        //수정할 데이터를 model에 담아줌
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
