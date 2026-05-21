package com.thanglong.vneid.domain.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Domain model đại diện cho phiên đăng nhập QR (khớp với bảng qr_login_sessions).
 */
public class QrLoginSession {

    private String qrToken;
    private String status;
    private String cccdNumber;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    public QrLoginSession() {
    }

    public QrLoginSession(String qrToken, String status, String cccdNumber, LocalDateTime createdAt,
            LocalDateTime expiresAt) {
        this.qrToken = qrToken;
        this.status = status;
        this.cccdNumber = cccdNumber;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public static QrLoginSessionBuilder builder() {
        return new QrLoginSessionBuilder();
    }

    public String getQrToken() {
        return qrToken;
    }

    public void setQrToken(String qrToken) {
        this.qrToken = qrToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCccdNumber() {
        return cccdNumber;
    }

    public void setCccdNumber(String cccdNumber) {
        this.cccdNumber = cccdNumber;
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

    public static class QrLoginSessionBuilder {
        private String qrToken;
        private String status;
        private String cccdNumber;
        private LocalDateTime createdAt;
        private LocalDateTime expiresAt;

        public QrLoginSessionBuilder qrToken(String qrToken) {
            this.qrToken = qrToken;
            return this;
        }

        public QrLoginSessionBuilder status(String status) {
            this.status = status;
            return this;
        }

        public QrLoginSessionBuilder cccdNumber(String cccdNumber) {
            this.cccdNumber = cccdNumber;
            return this;
        }

        public QrLoginSessionBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public QrLoginSessionBuilder expiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public QrLoginSession build() {
            return new QrLoginSession(qrToken, status, cccdNumber, createdAt, expiresAt);
        }
    }
}
