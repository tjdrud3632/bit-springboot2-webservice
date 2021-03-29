package com.example.web.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role { //Enum - 열거형/ 상수로 지정

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER","일반 사용자");
  //  ADMIN("ROLE_ADMIN", "관리자") // 역할_ - 스프링세큐리티 문법

    private final String key;
    private final String title;
}
