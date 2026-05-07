package com.thanglong.vneid.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * JPA Entity mapping với bảng qr_login_sessions trong vneid_simulator.sql.
 */
@Entity
@Table(name = "qr_login_sessions")
public class QrSessionEntity {

    @Id
    @Column(name = "qr_token", nullable = false)
    private String qrToken;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "cccd_number")
    private String cccdNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "firebase_uid")
    private String firebaseUid;

    public QrSessionEntity() {}

    public QrSessionEntity(String qrToken, String status, String cccdNumber, LocalDateTime createdAt, LocalDateTime expiresAt, String firebaseUid) {
        this.qrToken = qrToken;
        this.status = status;
        this.cccdNumber = cccdNumber;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.firebaseUid = firebaseUid;
    }

    public static QrSessionEntityBuilder builder() {
        return new QrSessionEntityBuilder();
    }

    public String getQrToken() { return qrToken; }
    public void setQrToken(String qrToken) { this.qrToken = qrToken; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCccdNumber() { return cccdNumber; }
    public void setCccdNumber(String cccdNumber) { this.cccdNumber = cccdNumber; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; }
    public String getFirebaseUid() { return firebaseUid; }
    public void setFirebaseUid(String firebaseUid) { this.firebaseUid = firebaseUid; }

    public static class QrSessionEntityBuilder {
        private String qrToken;
        private String status;
        private String cccdNumber;
        private LocalDateTime createdAt;
        private LocalDateTime expiresAt;
        private String firebaseUid;

        public QrSessionEntityBuilder qrToken(String qrToken) { this.qrToken = qrToken; return this; }
        public QrSessionEntityBuilder status(String status) { this.status = status; return this; }
        public QrSessionEntityBuilder cccdNumber(String cccdNumber) { this.cccdNumber = cccdNumber; return this; }
        public QrSessionEntityBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public QrSessionEntityBuilder expiresAt(LocalDateTime expiresAt) { this.expiresAt = expiresAt; return this; }
        public QrSessionEntityBuilder firebaseUid(String firebaseUid) { this.firebaseUid = firebaseUid; return this; }
        public QrSessionEntity build() {
            return new QrSessionEntity(qrToken, status, cccdNumber, createdAt, expiresAt, firebaseUid);
        }
    }
}
