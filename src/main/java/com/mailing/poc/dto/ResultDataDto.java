package com.mailing.poc.dto;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ResultDataDto {

    private List<StressDataDto> resultStressIndexAvgs;

    public ResultDataDto() {
        this.resultStressIndexAvgs = new ArrayList<>();
    }

    public ResultDataDto(List<StressDataDto> resultStressIndexAvgs) {
        this.resultStressIndexAvgs = resultStressIndexAvgs;
    }
}
