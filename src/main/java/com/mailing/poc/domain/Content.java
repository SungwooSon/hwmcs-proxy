package com.mailing.poc.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "Content", schema = "poc")
@AllArgsConstructor
public class Content {

    @Id
    private Long id;

    private int solutionId;
    private String title;
    private int price;
    private String body;
    private String thumbnailField;


}
