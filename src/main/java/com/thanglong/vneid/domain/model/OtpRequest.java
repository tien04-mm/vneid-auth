package com.thanglong.vneid.domain.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Domain model đại diện cho mã OTP xác thực (khớp với bảng otp_requests).
 */
public class OtpRequest {

    private Long id;
    private String cccdNumber;
    private String email;
    private String otpCode;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean used;

    public OtpRequest() {
    }

    public OtpRequest(Long id, String cccdNumber, String email, String otpCode, LocalDateTime createdAt,
            LocalDateTime expiresAt, boolean used) {
        this.id = id;
        this.cccdNumber = cccdNumber;
        this.email = email;
        this.otpCode = otpCode;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.used = used;
    }

    public static OtpRequestBuilder builder() {
        return new OtpRequestBuilder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCccdNumber() {
        return cccdNumber;
    }

    public void setCccdNumber(String cccdNumber) {
        this.cccdNumber = cccdNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public static class OtpRequestBuilder {
        private Long id;
        private String cccdNumber;
        private String email;
        private String otpCode;
        private LocalDateTime createdAt;
        private LocalDateTime expiresAt;
        private boolean used;

        public OtpRequestBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public OtpRequestBuilder cccdNumber(String cccdNumber) {
            this.cccdNumber = cccdNumber;
            return this;
        }

        public OtpRequestBuilder email(String email) {
            this.email = email;
            return this;
        }

        public OtpRequestBuilder otpCode(String otpCode) {
            this.otpCode = otpCode;
            return this;
        }

        public OtpRequestBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public OtpRequestBuilder expiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public OtpRequestBuilder used(boolean used) {
            this.used = used;
            return this;
        }

        public OtpRequest build() {
            return new OtpRequest(id, cccdNumber, email, otpCode, createdAt, expiresAt, used);
        }
    }
}
