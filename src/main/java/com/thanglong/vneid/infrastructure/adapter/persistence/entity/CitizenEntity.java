package com.thanglong.vneid.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

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

    /**
     * Khóa chính - Số Căn cước công dân (12 số).
     * Ánh xạ vào cột cccd_number trong bảng citizens.
     * Không dùng IDENTITY vì giá trị do ứng dụng/admin cung cấp, không auto-increment.
     */
    @Id
    @Column(name = "cccd_number", unique = true, nullable = false, length = 12)
    private String cccdNumber;

    @Column(name = "firebase_uid", length = 128)
    private String firebaseUid;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(name = "passcode", length = 6)
    private String passcode;

    @Column(name = "role", length = 20)
    private String role;

    @Column(name = "account_status", length = 20)
    private String accountStatus;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public CitizenEntity() {}

    public CitizenEntity(String cccdNumber, String firebaseUid, String fullName, LocalDate dateOfBirth, String gender, String address, String phoneNumber, String email, String password, String passcode, String role, String accountStatus, String avatarUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.cccdNumber = cccdNumber;
        this.firebaseUid = firebaseUid;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.passcode = passcode;
        this.role = role;
        this.accountStatus = accountStatus;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPasscode() { return passcode; }
    public void setPasscode(String passcode) { this.passcode = passcode; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getAccountStatus() { return accountStatus; }
    public void setAccountStatus(String accountStatus) { this.accountStatus = accountStatus; }
    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public static class CitizenEntityBuilder {
        private String cccdNumber;
        private String firebaseUid;
        private String fullName;
        private LocalDate dateOfBirth;
        private String gender;
        private String address;
        private String phoneNumber;
        private String email;
        private String password;
        private String passcode;
        private String role;
        private String accountStatus;
        private String avatarUrl;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public CitizenEntityBuilder cccdNumber(String cccdNumber) { this.cccdNumber = cccdNumber; return this; }
        public CitizenEntityBuilder firebaseUid(String firebaseUid) { this.firebaseUid = firebaseUid; return this; }
        public CitizenEntityBuilder fullName(String fullName) { this.fullName = fullName; return this; }
        public CitizenEntityBuilder dateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; return this; }
        public CitizenEntityBuilder gender(String gender) { this.gender = gender; return this; }
        public CitizenEntityBuilder address(String address) { this.address = address; return this; }
        public CitizenEntityBuilder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public CitizenEntityBuilder email(String email) { this.email = email; return this; }
        public CitizenEntityBuilder password(String password) { this.password = password; return this; }
        public CitizenEntityBuilder passcode(String passcode) { this.passcode = passcode; return this; }
        public CitizenEntityBuilder role(String role) { this.role = role; return this; }
        public CitizenEntityBuilder accountStatus(String accountStatus) { this.accountStatus = accountStatus; return this; }
        public CitizenEntityBuilder avatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; return this; }
        public CitizenEntityBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }
        public CitizenEntityBuilder updatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; return this; }
        public CitizenEntity build() {
            return new CitizenEntity(cccdNumber, firebaseUid, fullName, dateOfBirth, gender, address, phoneNumber, email, password, passcode, role, accountStatus, avatarUrl, createdAt, updatedAt);
        }
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
