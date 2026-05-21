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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cccd_number", referencedColumnName = "cccd_number")
    private CitizenEntity citizen;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    public QrLoginSessionEntity() {
    }

    public QrLoginSessionEntity(String qrToken, String status, CitizenEntity citizen, LocalDateTime createdAt,
            LocalDateTime expiresAt) {
        this.qrToken = qrToken;
        this.status = status;
        this.citizen = citizen;
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

    public CitizenEntity getCitizen() {
        return citizen;
    }

    public void setCitizen(CitizenEntity citizen) {
        this.citizen = citizen;
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
        private CitizenEntity citizen;
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

        public QrLoginSessionEntityBuilder citizen(CitizenEntity citizen) {
            this.citizen = citizen;
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
            return new QrLoginSessionEntity(qrToken, status, citizen, createdAt, expiresAt);
        }
    }
}
