package com.mailing.poc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mailing.poc.domain.PurchaseContent;
import com.mailing.poc.domain.User;
import com.mailing.poc.dto.PurchaseInfoDto;
import com.mailing.poc.dto.UserInfoDto;
import com.mailing.poc.service.FlowlyEmailService;
import com.mailing.poc.service.PurchaseContentService;
import com.mailing.poc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PurchaseContentController {

    private final PurchaseContentService purchaseContentService;
    private final UserService userService;
    private final FlowlyEmailService emailService;

    @GetMapping("/flowly/purchase/{id}")
    public void purchase(@PathVariable Long id) throws JsonProcessingException {

        PurchaseContent purchaseContent = purchaseContentService.getPurchaseContent(id);
        PurchaseInfoDto purchaseInfoDto = PurchaseInfoDto.from(purchaseContent.getInfo());

        User user = userService.getUser(purchaseContent.getUserId());
        UserInfoDto userInfoDto = UserInfoDto.from(user);

        emailService.sendPurcahseInfo(purchaseInfoDto, userInfoDto);
    }
}
