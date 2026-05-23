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
        try {
            jakarta.mail.internet.MimeMessage message = mailSender.createMimeMessage();
            org.springframework.mail.javamail.MimeMessageHelper helper = new org.springframework.mail.javamail.MimeMessageHelper(message, true, "UTF-8");
            
            helper.setTo(to);
            helper.setSubject("[VNeID] Mã xác thực OTP");
            
            String htmlContent = buildOtpEmailTemplate(to, otpCode);
            helper.setText(htmlContent, true);
            
            mailSender.send(message);
            log.info("OTP HTML email sent to {}", to);
        } catch (Exception e) {
            log.error("Failed to send OTP HTML email to {}: {}. OTP code was: {}", to, e.getMessage(), otpCode);
        }
    }

    private String buildOtpEmailTemplate(String email, String otpCode) {
        // Lấy tên từ email nếu không có tên người dùng (ví dụ: nguyenvan_a@gmail.com -> nguyenvan_a)
        String username = email.contains("@") ? email.split("@")[0] : "Quý công dân";
        
        return "<!DOCTYPE html>\n" +
               "<html>\n" +
               "<head>\n" +
               "    <style>\n" +
               "        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f9fafb; margin: 0; padding: 0; }\n" +
               "        .container { max-width: 600px; margin: 40px auto; background-color: #ffffff; padding: 30px; border-radius: 10px; box-shadow: 0 4px 15px rgba(0,0,0,0.05); border-top: 5px solid #dc2626; }\n" +
               "        .header { text-align: center; border-bottom: 2px solid #f3f4f6; padding-bottom: 20px; margin-bottom: 25px; }\n" +
               "        .header h2 { color: #dc2626; margin: 0; font-size: 24px; font-weight: 700; }\n" +
               "        .content { font-size: 16px; color: #374151; line-height: 1.6; }\n" +
               "        .otp-container { text-align: center; margin: 35px 0; }\n" +
               "        .otp-code { font-size: 36px; font-weight: 800; color: #dc2626; letter-spacing: 6px; background-color: #fef2f2; padding: 15px 30px; border-radius: 8px; display: inline-block; border: 1px dashed #fca5a5; }\n" +
               "        .warning { background-color: #fffbeb; color: #92400e; padding: 16px; border-left: 4px solid #f59e0b; margin-top: 30px; font-size: 14.5px; border-radius: 0 8px 8px 0; }\n" +
               "        .warning strong { color: #b45309; }\n" +
               "        .footer { margin-top: 40px; text-align: center; font-size: 13px; color: #9ca3af; border-top: 2px solid #f3f4f6; padding-top: 20px; }\n" +
               "        .footer p { margin: 5px 0; }\n" +
               "    </style>\n" +
               "</head>\n" +
               "<body>\n" +
               "    <div class=\"container\">\n" +
               "        <div class=\"header\">\n" +
               "            <h2>VNeID - Hệ thống Định danh điện tử Quốc gia</h2>\n" +
               "        </div>\n" +
               "        <div class=\"content\">\n" +
               "            <p>Xin chào <strong>" + username + "</strong>,</p>\n" +
               "            <p>Hệ thống VNeID vừa nhận được yêu cầu lấy mã xác thực (OTP) từ bạn. Dưới đây là mã xác thực của bạn:</p>\n" +
               "            <div class=\"otp-container\">\n" +
               "                <span class=\"otp-code\">" + otpCode + "</span>\n" +
               "            </div>\n" +
               "            <p style=\"text-align: center; margin-top: -15px;\"><em>Mã này có hiệu lực trong <strong>5 phút</strong>.</em></p>\n" +
               "            \n" +
               "            <div class=\"warning\">\n" +
               "                <strong>⚠️ LƯU Ý BẢO MẬT QUAN TRỌNG:</strong> Tuyệt đối không chia sẻ mã này với bất kỳ ai, kể cả người tự xưng là cán bộ công an hay nhân viên hỗ trợ VNeID. Việc chia sẻ mã này có thể khiến bạn mất tài khoản.\n" +
               "            </div>\n" +
               "        </div>\n" +
               "        <div class=\"footer\">\n" +
               "            <p>Email này được gửi tự động từ hệ thống VNeID. Vui lòng không trả lời email này.</p>\n" +
               "        </div>\n" +
               "    </div>\n" +
               "</body>\n" +
               "</html>";
    }

    @Override
    public void sendActivationEmail(String to, String activationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("[VNeID] Kích hoạt tài khoản");
        message.setText("Nhấn vào link sau để kích hoạt tài khoản: " + activationLink);
        try {
            mailSender.send(message);
            log.info("Activation email sent to {}", to);
        } catch (Exception e) {
            log.error("Failed to send activation email to {}: {}", to, e.getMessage());
        }
    }

    @Override
    public void sendPasswordResetEmail(String to, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("[VNeID] Đặt lại mật khẩu");
        message.setText("Nhấn vào link sau để đặt lại mật khẩu: " + resetLink);
        try {
            mailSender.send(message);
            log.info("Password reset email sent to {}", to);
        } catch (Exception e) {
            log.error("Failed to send password reset email to {}: {}", to, e.getMessage());
        }
    }
}
