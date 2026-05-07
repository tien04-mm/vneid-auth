package com.thanglong.vneid.infrastructure.adapter.external;

import com.thanglong.vneid.usecase.port.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Triển khai gửi email qua Spring Mail.
 */
@Service
public class EmailServiceAdapter implements EmailService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EmailServiceAdapter.class);

    private final JavaMailSender mailSender;

    public EmailServiceAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendOtpEmail(String to, String otpCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("[VNeID] Mã xác thực OTP");
        message.setText("Mã OTP của bạn là: " + otpCode + "\nMã có hiệu lực trong 5 phút.");
        mailSender.send(message);
        log.info("OTP email sent to {}", to);
    }

    @Override
    public void sendActivationEmail(String to, String activationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("[VNeID] Kích hoạt tài khoản");
        message.setText("Nhấn vào link sau để kích hoạt tài khoản: " + activationLink);
        mailSender.send(message);
        log.info("Activation email sent to {}", to);
    }

    @Override
    public void sendPasswordResetEmail(String to, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("[VNeID] Đặt lại mật khẩu");
        message.setText("Nhấn vào link sau để đặt lại mật khẩu: " + resetLink);
        mailSender.send(message);
        log.info("Password reset email sent to {}", to);
    }
}
