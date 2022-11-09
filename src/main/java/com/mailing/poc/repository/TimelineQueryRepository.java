package com.mailing.poc.repository;

import com.mailing.poc.dto.StressDataDto;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.mailing.poc.domain.QTimeline.timeline;
import static com.mailing.poc.domain.QUser.user;

@RequiredArgsConstructor
@Repository
public class TimelineQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<StressDataDto> getAvgStressIndexByType(LocalDateTime start, LocalDateTime end, String type) {

        StringPath searchType;
        List<String> searchList;
        Map<String, Long> map = new LinkedHashMap<>();
        List<StressDataDto> list = new ArrayList<>();

        if (type.equals("jobGrade")) {
            searchType = timeline.user.jobGrade;
            searchList = Arrays.asList("임원", "부장", "차장", "과장", "대리", "사원", "매니저", "기타");
            searchList.stream().forEach(s -> {
                map.put(s, 0l);
            });
        } else if (type.equals("jobGroup")) {
            searchType = timeline.user.jobGroup;
            searchList = Arrays.asList("IT/데이터", "기획관리", "대고객", "상품서비스", "영업/마케팅", "지원", "투자/회계", "기타");
            searchList.stream().forEach(s -> {
                map.put(s, 0l);
            });
        } else {
            throw new IllegalStateException("type은 jobGrade, jobGroup 만 가능");
        }

        List<Tuple> fetch = queryFactory.select(searchType, timeline.stressIndex.avg())
                .from(timeline)
                .leftJoin(timeline.user, user)
                .where(
                        timeline.user.firstLogin.isNotNull()
                                .and(timeline.createdAt.between(start, end))
                                .and(searchType.in(searchList))
                )
                .groupBy(searchType)
                .fetch();

        fetch.stream().forEach(tuple -> {
            map.put(tuple.get(searchType), Math.round(tuple.get(timeline.stressIndex.avg())));
        });

        Double fetchOne = queryFactory.select(timeline.stressIndex.avg())
                .from(timeline)
                .leftJoin(timeline.user)
                .where(timeline.user.firstLogin.isNotNull()
                        .and(timeline.createdAt.between(start, end))
                        .and(searchType.notIn(searchList)))
                .fetchOne();

        if(fetchOne != null) {
            map.put("기타", Math.round(fetchOne.doubleValue()));
        }

        map.forEach((s,l) -> {
            list.add(new StressDataDto(s, l));
        });

        return list;
    }
}
