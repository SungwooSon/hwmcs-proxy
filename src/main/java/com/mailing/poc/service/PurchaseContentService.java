package com.mailing.poc.service;

import com.mailing.poc.domain.PurchaseContent;
import com.mailing.poc.repository.PurchaseContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PurchaseContentService {

    private final PurchaseContentRepository purchaseContentRepository;

    public PurchaseContent getPurchaseContent(Long id) {
        return purchaseContentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("구매내역이 존재하지 않음"));
    }
}
