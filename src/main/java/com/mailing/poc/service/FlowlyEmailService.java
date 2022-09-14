package com.mailing.poc.service;

import com.mailing.poc.dto.EmailMessage;
import com.mailing.poc.dto.PurchaseInfoDto;
import com.mailing.poc.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class FlowlyEmailService {

    private static final String EXAMPLE_LINK_TEMPLATE = "flowlyTemplate";

    private final TemplateEngine templateEngine;

    private final EmailService emailService;

    public void sendPurcahseInfo(PurchaseInfoDto purchaseInfoDto, UserInfoDto userInfoDto) {
        Context context = getContext(purchaseInfoDto, userInfoDto);
        String message = templateEngine.process(EXAMPLE_LINK_TEMPLATE, context);

        EmailMessage emailMessage = EmailMessage.builder()
                .to("help@flowly.world")
                //.to("ssw0418@kakao.com")
                .subject("flowly 신청정보")
                .message(message)
                .build();
        emailService.send(emailMessage);
    }

    private Context getContext(PurchaseInfoDto purchaseInfoDto, UserInfoDto userInfoDto) {
        Context context = new Context();

        context.setVariable("name", userInfoDto.getName());
        context.setVariable("flowlyAccount", userInfoDto.getFlowlyAccount());

        context.setVariable("contact", purchaseInfoDto.getContact());
        context.setVariable("postCode", purchaseInfoDto.getPostCode());
        context.setVariable("address", purchaseInfoDto.getAddress());
        context.setVariable("detailAddress", purchaseInfoDto.getDetailAddress());
        return context;
    }
}
