package com.mailing.poc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString(of = {"desc", "stressIndex"})
public class StressDataDto {
    private String desc;
    private Long stressIndex;
}
