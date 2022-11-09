package com.mailing.poc.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "User", schema = "poc")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    private String email;
    private String password;
    private String name;
    private String jobGroup;
    private String jobGrade;
    private int gender;
    private int age;
    private String area1;
    private String area2;
    private int pushAlarm;
    private LocalDateTime marketingTerm;
    private LocalDateTime privacyTerm;
    private LocalDateTime firstLogin;
    private String fcmToken;
    private LocalDateTime fcmTokenUpdatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private String thirdPartyEmail;

}
