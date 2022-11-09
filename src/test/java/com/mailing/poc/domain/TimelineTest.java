package com.mailing.poc.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TimelineTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void findTest() {

        String jpql = "select t from Timeline t join t.user u where u.id = :id";

        List<Timeline> timelines = em.createQuery(jpql, Timeline.class)
                .setParameter("id", 14l)
                .getResultList();

        timelines.stream()
                .forEach(t -> {
                    System.out.println("t = " + t.getStressIndex());
                    System.out.println("t->grade = " + t.getUser().getJobGrade());
                    System.out.println("t->group = " + t.getUser().getJobGroup());
                });

    }




}