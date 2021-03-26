package com.example.web.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //상속받은 클래스들은 필드 사용이 가능함
@EntityListeners(AuditingEntityListener.class) //jpa의 에노테이션 - Entity의 이벤트를 감지하는 에노테이션(관찰)
public abstract class BaseTimeEntity { //표준을 제공해주기 위한 추상클래스

    @CreatedDate //생성될 때의 시간을 자동으로 저장함
    private LocalDateTime createDate;

    @LastModifiedDate //마지막 수정 시간을 자동으로 저장함
    private LocalDateTime modifiedDate;
}
