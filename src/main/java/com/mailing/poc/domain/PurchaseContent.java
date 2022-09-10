package com.mailing.poc.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "PurchaseContent", schema = "poc")
public class PurchaseContent {
    @Id
    private Long id;
    private Long userId;
    private Long contentId;
    private Integer status;
    private String info;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
