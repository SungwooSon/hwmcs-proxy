package com.mailing.poc.repository;

import com.mailing.poc.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ContentRepository extends JpaRepository<Content, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Content c set c.price = 999 where c.solutionId = 2")
    int updateContentPrice();
}
