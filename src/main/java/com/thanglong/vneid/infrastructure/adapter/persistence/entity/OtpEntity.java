package com.thanglong.vneid.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * JPA Entity mapping với bảng otps trong vneid_simulator.sql.
 */
@Entity
@Table(name = "otps")
public class OtpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cccd_number", length = 12)
    private String cccdNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "otp_code", nullable = false, length = 6)
    private String otpCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "used")
    private boolean used;

    public OtpEntity() {}

    public OtpEntity(Long id, String cccdNumber, String email, String otpCode, LocalDateTime createdAt, LocalDateTime expiresAt, boolean used) {
        this.id = id;
        this.cccdNumber = cccdNumber;
        this.email = email;
        this.otpCode = otpCode;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.used = used;
    }

    public static OtpEntityBuilder builder() {
        return new OtpEntityBuilder();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCccdNumber() { return cccdNumber; }
    public void setCccdNumber(String cccdNumber) { this.cccdNumber = cccdNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getOtpCode() { return otpCode; }
    public void setOtpCode(String otpCode) { this.otpCode = otpCode; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }

    public static class OtpEntityBuilder {
        private Long id;
        private String cccdNumber;
        private String email;
        private String otpCode;
        private LocalDateTime createdAt;
        private LocalDateTime expiresAt;
        private boolean used;

        public OtpEntityBuilder id(Long id) { this.id = id; return this; }
        public OtpEntityBuilder cccdNumber(String cccdNumber) { this.cccdNumber = cccdNumber; return this; }
        public OtpEntityBuilder email(String email) { this.email = email; return this; }
        public OtpEntityBuilder otpCode(String otpCode) { this.otpCode = otpCode; return this; }
        public OtpEntityBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public OtpEntityBuilder expiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; return this; }
        public OtpEntityBuilder used(boolean used) { this.used = used; return this; }
        public OtpEntity build() {
            return new OtpEntity(id, cccdNumber, email, otpCode, createdAt, expiresAt, used);
        }
    }
}
