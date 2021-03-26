package com.example.web.springboot;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//서버 역할
@EnableJpaAuditing //jpa Auditing을 활성화시키는 기능
@SpringBootApplication
public class Application {
    public static void main(String[] args){

        SpringApplication.run(Application.class, args);
    }


}
//스프링부트 안에 있는 wAS(web Aplication Server) 를 사용한다 (톰캣의 설치가 필요없음)