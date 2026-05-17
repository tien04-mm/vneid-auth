package com.thanglong.vneid.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Domain model đại diện cho thông tin công dân.
 * Đây là thực thể nghiệp vụ thuần túy, không phụ thuộc vào framework.
 */
public class Citizen {
    private String cccdNumber; // Số CCCD (Khóa chính)
    private String firebaseUid; // Firebase UID
    private String fullName;
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private String passcode; // Mã PIN 6 số
    private String role; // ROLE_CITIZEN, ROLE_ADMIN
    private String accountStatus;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Citizen() {
    }

    public Citizen(String cccdNumber, String firebaseUid, String fullName, LocalDate dateOfBirth, String gender,
            String address, String phoneNumber, String email, String password, String passcode, String role,
            String accountStatus, String avatarUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    public static CitizenBuilder builder() {
        return new CitizenBuilder();
    }

    public String getCccdNumber() {
        return cccdNumber;
    }

    public void setCccdNumber(String cccdNumber) {
        this.cccdNumber = cccdNumber;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class CitizenBuilder {
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

        public CitizenBuilder cccdNumber(String cccdNumber) {
            this.cccdNumber = cccdNumber;
            return this;
        }

        public CitizenBuilder firebaseUid(String firebaseUid) {
            this.firebaseUid = firebaseUid;
            return this;
        }

        public CitizenBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public CitizenBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public CitizenBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public CitizenBuilder address(String address) {
            this.address = address;
            return this;
        }

        public CitizenBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CitizenBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CitizenBuilder password(String password) {
            this.password = password;
            return this;
        }

        public CitizenBuilder passcode(String passcode) {
            this.passcode = passcode;
            return this;
        }

        public CitizenBuilder role(String role) {
            this.role = role;
            return this;
        }

        public CitizenBuilder accountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
            return this;
        }

        public CitizenBuilder avatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
            return this;
        }

        public CitizenBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CitizenBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Citizen build() {
            return new Citizen(cccdNumber, firebaseUid, fullName, dateOfBirth, gender, address, phoneNumber, email,
                    password, passcode, role, accountStatus, avatarUrl, createdAt, updatedAt);
        }
    }
}
