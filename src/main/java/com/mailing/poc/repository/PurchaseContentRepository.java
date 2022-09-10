package com.mailing.poc.repository;

import com.mailing.poc.domain.PurchaseContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseContentRepository extends JpaRepository<PurchaseContent, Long> {
}
