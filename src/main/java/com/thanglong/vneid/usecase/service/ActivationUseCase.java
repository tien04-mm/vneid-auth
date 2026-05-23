package com.thanglong.vneid.usecase.service;

import com.thanglong.vneid.domain.exception.AccountAlreadyActivatedException;
import com.thanglong.vneid.domain.model.Citizen;
import com.thanglong.vneid.domain.model.OtpRequest;
import com.thanglong.vneid.domain.repository.CitizenRepository;
import com.thanglong.vneid.domain.repository.OtpRequestRepository;
import com.thanglong.vneid.usecase.port.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ActivationUseCase {

    private final CitizenRepository citizenRepository;
    private final OtpRequestRepository otpRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public ActivationUseCase(CitizenRepository citizenRepository, OtpRequestRepository otpRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.citizenRepository = citizenRepository;
        this.otpRepository = otpRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Bước 1: Yêu cầu kích hoạt.
     * Kiểm tra CCCD và SĐT, gửi OTP về Email.
     */
    public String requestActivation(String cccdNumber, String phoneNumber, String email) {
        Citizen citizen = citizenRepository.findByCccdNumber(cccdNumber)
                .orElseThrow(() -> new RuntimeException("Số CCCD không tồn tại trong hệ thống"));

        if (citizen.getPhoneNumber() == null || !phoneNumber.equals(citizen.getPhoneNumber())) {
            throw new RuntimeException("Số điện thoại không khớp với dữ liệu dân cư");
        }

        if (citizen.getEmail() == null || !email.equalsIgnoreCase(citizen.getEmail())) {
            throw new RuntimeException("Email không khớp với dữ liệu dân cư");
        }

        if ("ACTIVE".equals(citizen.getAccountStatus())) {
            throw new AccountAlreadyActivatedException("Tài khoản đã được kích hoạt");
        }

        // Sinh OTP
        String otpCode = String.format("%06d", new java.util.Random().nextInt(1000000));
        
        OtpRequest otp = OtpRequest.builder()
                .cccdNumber(cccdNumber)
                .email(email)
                .otpCode(otpCode)
                .createdAt(java.time.LocalDateTime.now())
                .expiresAt(java.time.LocalDateTime.now().plusMinutes(5))
                .used(false)
                .build();

        otpRepository.save(otp);
        emailService.sendOtpEmail(email, otpCode);

        // Masked email: t***@gmail.com
        return maskEmail(email);
    }

    /**
     * Bước 2: Xác thực OTP.
     */
    public void verifyOtp(String cccdNumber, String otpCode) {
        OtpRequest otp = otpRepository.findByCccdNumberAndOtpCode(cccdNumber, otpCode)
                .orElseThrow(() -> new RuntimeException("Mã OTP không hợp lệ"));

        if (otp.isUsed()) {
            throw new RuntimeException("Mã OTP đã được sử dụng");
        }

        if (otp.getExpiresAt().isBefore(java.time.LocalDateTime.now())) {
            throw new RuntimeException("Mã OTP đã hết hạn");
        }

        otp.setUsed(true);
        otpRepository.save(otp);
    }

    /**
     * Bước 3: Đặt mật khẩu và mã PIN.
     */
    public Citizen setPassword(String cccdNumber, String password, String passcode) {
        Citizen citizen = citizenRepository.findByCccdNumber(cccdNumber)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin công dân"));

        citizen.setPasswordHash(passwordEncoder.encode(password));
        citizen.setPasscodeHash(passwordEncoder.encode(passcode));
        citizen.setAccountStatus("ACTIVE");

        return citizenRepository.save(citizen);
    }

    private String maskEmail(String email) {
        int atIndex = email.indexOf("@");
        if (atIndex <= 1) return email;
        String prefix = email.substring(0, 1);
        String domain = email.substring(atIndex);
        return prefix + "***" + domain;
    }
}
