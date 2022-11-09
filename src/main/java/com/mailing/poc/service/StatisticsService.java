package com.mailing.poc.service;

import com.mailing.poc.dto.StressDataDto;
import com.mailing.poc.repository.TimelineQueryRepository;
import com.querydsl.core.types.dsl.StringPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final TimelineQueryRepository timelineQueryRepository;

    public List<StressDataDto> getAvgStressIndexByType(String start, String end, String type) {
        return timelineQueryRepository.getAvgStressIndexByType(parseDateZeroTimeFormat(start), addDate(parseDateZeroTimeFormat(end),1), type);
    }

    private LocalDateTime parseDateZeroTimeFormat(String date) {

        String[] datetimeArr = date.split(" ");
        String[] dateArr = datetimeArr[0].split("-");

        return LocalDateTime.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]), 0, 0, 0, 0);
    }

    private LocalDateTime addDate(LocalDateTime date, int addCount) {
        return date.plusDays(addCount);
    }
}
