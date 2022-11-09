package com.mailing.poc.controller;

import com.mailing.poc.dto.ResultDataDto;
import com.mailing.poc.dto.StressDataDto;
import com.mailing.poc.response.ResponseDto;
import com.mailing.poc.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/statistics/stress/{type}/average")
    public ResponseEntity<ResponseDto> getAverageStress(@PathVariable(value = "type") String type
                                , @RequestParam(value = "start") String start
                                , @RequestParam(value = "end") String end) {

        List<StressDataDto> resultList = statisticsService.getAvgStressIndexByType(start, end, type);

        return new ResponseEntity<>(new ResponseDto("", new ResultDataDto(resultList), ""), HttpStatus.OK);
    }

}
