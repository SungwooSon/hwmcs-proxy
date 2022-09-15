package com.mailing.poc.service;

import com.mailing.poc.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    public void send(EmailMessage emailMessage) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            /**
             * 첨부 파일(Multipartfile) 보낼거면 true
             */
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(emailMessage.getTo());
            mimeMessageHelper.setSubject(emailMessage.getSubject());
            /**
             * html 템플릿으로 보낼거면 true
             * plaintext로 보낼거면 false
             */
            mimeMessageHelper.setText(emailMessage.getMessage(), false);
            javaMailSender.send(mimeMessage);
            log.info("sent email: {}", emailMessage.getMessage());
        } catch (MessagingException e) {
            log.error("[EmailService.send()] error {}", e.getMessage());
        }
    }
}
