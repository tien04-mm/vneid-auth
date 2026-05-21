package com.thanglong.vneid.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * JPA Entity mapping với bảng citizens trong vneid_simulator.sql.
 *
 * <p>Khóa chính: cccd_number (số CCCD 12 số) - kiểu VARCHAR, không auto-generate.</p>
 */
@Entity
@Table(name = "citizens")
public class CitizenEntity {

    @Id
    @Column(name = "cccd_number", unique = true, nullable = false, length = 12)
    private String cccdNumber;

    @Column(name = "firebase_uid", length = 128)
    private String firebaseUid;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "passcode_hash")
    private String passcodeHash;

    @Column(name = "account_status", length = 20)
    private String accountStatus;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "citizen", fetch = FetchType.LAZY)
    private java.util.List<OtpRequestEntity> otpRequests;

    @OneToMany(mappedBy = "citizen", fetch = FetchType.LAZY)
    private java.util.List<QrLoginSessionEntity> qrLoginSessions;

    public CitizenEntity() {}

    public CitizenEntity(String cccdNumber, String firebaseUid, String fullName, LocalDate dob, String gender,
            String phoneNumber, String email, String passwordHash, String passcodeHash,
            String accountStatus, LocalDateTime createdAt, java.util.List<OtpRequestEntity> otpRequests, java.util.List<QrLoginSessionEntity> qrLoginSessions) {
        this.cccdNumber = cccdNumber;
        this.firebaseUid = firebaseUid;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passcodeHash = passcodeHash;
        this.accountStatus = accountStatus;
        this.createdAt = createdAt;
        this.otpRequests = otpRequests;
        this.qrLoginSessions = qrLoginSessions;
    }

    public static CitizenEntityBuilder builder() {
        return new CitizenEntityBuilder();
    }

    public String getCccdNumber() { return cccdNumber; }
    public void setCccdNumber(String cccdNumber) { this.cccdNumber = cccdNumber; }
    public String getFirebaseUid() { return firebaseUid; }
    public void setFirebaseUid(String firebaseUid) { this.firebaseUid = firebaseUid; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getPasscodeHash() { return passcodeHash; }
    public void setPasscodeHash(String passcodeHash) { this.passcodeHash = passcodeHash; }
    public String getAccountStatus() { return accountStatus; }
    public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public java.util.List<OtpRequestEntity> getOtpRequests() { return otpRequests; }
    public void setOtpRequests(java.util.List<OtpRequestEntity> otpRequests) { this.otpRequests = otpRequests; }
    public java.util.List<QrLoginSessionEntity> getQrLoginSessions() { return qrLoginSessions; }
    public void setQrLoginSessions(java.util.List<QrLoginSessionEntity> qrLoginSessions) { this.qrLoginSessions = qrLoginSessions; }

    public static class CitizenEntityBuilder {
        private String cccdNumber;
        private String firebaseUid;
        private String fullName;
        private LocalDate dob;
        private String gender;
        private String phoneNumber;
        private String email;
        private String passwordHash;
        private String passcodeHash;
        private String accountStatus;
        private LocalDateTime createdAt;
        private java.util.List<OtpRequestEntity> otpRequests;
        private java.util.List<QrLoginSessionEntity> qrLoginSessions;

        public CitizenEntityBuilder cccdNumber(String cccdNumber) { this.cccdNumber = cccdNumber; return this; }
        public CitizenEntityBuilder firebaseUid(String firebaseUid) { this.firebaseUid = firebaseUid; return this; }
        public CitizenEntityBuilder fullName(String fullName) { this.fullName = fullName; return this; }
        public CitizenEntityBuilder dob(LocalDate dob) { this.dob = dob; return this; }
        public CitizenEntityBuilder gender(String gender) { this.gender = gender; return this; }
        public CitizenEntityBuilder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public CitizenEntityBuilder email(String email) { this.email = email; return this; }
        public CitizenEntityBuilder passwordHash(String passwordHash) { this.passwordHash = passwordHash; return this; }
        public CitizenEntityBuilder passcodeHash(String passcodeHash) { this.passcodeHash = passcodeHash; return this; }
        public CitizenEntityBuilder accountStatus(String accountStatus) { this.accountStatus = accountStatus; return this; }
        public CitizenEntityBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public CitizenEntityBuilder otpRequests(java.util.List<OtpRequestEntity> otpRequests) { this.otpRequests = otpRequests; return this; }
        public CitizenEntityBuilder qrLoginSessions(java.util.List<QrLoginSessionEntity> qrLoginSessions) { this.qrLoginSessions = qrLoginSessions; return this; }
        public CitizenEntity build() {
            return new CitizenEntity(cccdNumber, firebaseUid, fullName, dob, gender, phoneNumber, email, passwordHash, passcodeHash, accountStatus, createdAt, otpRequests, qrLoginSessions);
        }
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
