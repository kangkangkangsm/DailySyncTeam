package com.dailySync.schedule.entities;

import com.dailySync.common.BaseEntity;
import com.dailySync.user.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Entity //이 클래스가 JPA엔티티임을 나타냄,
@Getter //Lombok어노테이션으로 모든 필드에 대한 getter메소드를 자동으로 생성함으로써 속성을 외부에서 읽을 수 있도록 제공
@NoArgsConstructor //매개변수가 없는 기본 생성자 생성
@AllArgsConstructor //클래스의 모든 필드를 매개변수로 받는 생성자를 자동으로 생성해주는 역할
public class Schedule extends BaseEntity {
    //schedule 테이블을 표현한 것이며, 데이터베에스와 상호작용
//    @Column(nullable = false) //널을 허용하지 않는
//    private String userId; //필드을 정의한 것이고 데이터베이스의 테이블의 컬럼과 메핑

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

