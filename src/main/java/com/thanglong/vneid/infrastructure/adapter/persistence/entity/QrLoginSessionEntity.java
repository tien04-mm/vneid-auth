package com.thanglong.vneid.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * JPA Entity mapping với bảng qr_login_sessions.
 */
@Entity
@Table(name = "qr_login_sessions")
public class QrLoginSessionEntity {

    @Id
    @Column(name = "qr_token", nullable = false)
    private String qrToken;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "cccd_number", length = 12)
    private String cccdNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    public QrLoginSessionEntity() {
    }

    public QrLoginSessionEntity(String qrToken, String status, String cccdNumber, LocalDateTime createdAt,
            LocalDateTime expiresAt) {
        this.qrToken = qrToken;
        this.status = status;
        this.cccdNumber = cccdNumber;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public static QrLoginSessionEntityBuilder builder() {
        return new QrLoginSessionEntityBuilder();
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

    public static class QrLoginSessionEntityBuilder {
        private String qrToken;
        private String status;
        private String cccdNumber;
        private LocalDateTime createdAt;
        private LocalDateTime expiresAt;

        public QrLoginSessionEntityBuilder qrToken(String qrToken) {
            this.qrToken = qrToken;
            return this;
        }

        public QrLoginSessionEntityBuilder status(String status) {
            this.status = status;
            return this;
        }

        public QrLoginSessionEntityBuilder cccdNumber(String cccdNumber) {
            this.cccdNumber = cccdNumber;
            return this;
        }

        public QrLoginSessionEntityBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public QrLoginSessionEntityBuilder expiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public QrLoginSessionEntity build() {
            return new QrLoginSessionEntity(qrToken, status, cccdNumber, createdAt, expiresAt);
        }
    }
}
