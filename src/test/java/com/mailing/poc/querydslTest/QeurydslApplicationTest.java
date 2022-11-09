package com.mailing.poc.querydslTest;

import com.mailing.poc.domain.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import groovy.util.logging.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QeurydslApplicationTest {

    @Autowired
    EntityManager em;

    @Test
    public void contextLoads() {

        Coupon hello = new Coupon("xxxyyy", 1, LocalDateTime.now());
        em.persist(hello);

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QCoupon qHello = QCoupon.coupon;


        //assertThat(result).isEqualTo(hello);
        //assertThat(result.getId()).isEqualTo(hello.getId());
    }


    @Test
    public void startQuerydsl() {
        //String jpql = "select t from Timeline t join t.user u where u.id = :id";

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QTimeline t = new QTimeline("t");

        Timeline timeline = jpaQueryFactory.selectFrom(t)
                .where(t.user.id.eq(14l))
                .fetchOne();

        assertThat(timeline.getUser().getName()).isEqualTo("손성우 매니저");


    }

    @Test
    public void getAverageStress() {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);

        QTimeline timeline = QTimeline.timeline;
        QUser user = QUser.user;

        LocalDateTime start = LocalDateTime.of(2022, 9, 15, 0,0);
        LocalDateTime end = LocalDateTime.now();


        List<Tuple> fetch = jpaQueryFactory.select(timeline.user.jobGroup, timeline.stressIndex.avg())
                .from(timeline)
                .leftJoin(timeline.user, user)
                //.join(timeline.user)
                .where(timeline.user.firstLogin.isNotNull()
                        //.and(user.jobGroup.in()
                        .and(timeline.createdAt.between(start, end))
                        .and(timeline.user.jobGroup.in(Arrays.asList("IT/데이터", "기획관리", "대고객", "상품서비스", "영업/마케팅", "지원", "투자/회계")))
                )
                .groupBy(timeline.user.jobGroup)
                .fetch();

        fetch.stream().forEach(tuple -> {
            System.out.println("jogGrade, avgStressIndex : " + tuple.get(timeline.user.jobGroup) + ", " + tuple.get(timeline.stressIndex.avg()));
        });

        List<Double> fetch2 = jpaQueryFactory.select(timeline.stressIndex.avg())
                .from(timeline)
                .leftJoin(timeline.user)
                .where(timeline.user.firstLogin.isNotNull()
                        .and(timeline.createdAt.between(start, end))
                        .and(timeline.user.jobGroup.notIn(Arrays.asList("IT/데이터", "기획관리", "대고객", "상품서비스", "영업/마케팅", "지원", "투자/회계"))))
                .fetch();



        fetch2.stream().forEach(f -> {
            System.out.println("jogGrade, avgStressIndex : " + f);
        });
    }

}
