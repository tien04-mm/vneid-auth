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
    private LocalDate dob;
    private String gender;
    private String phoneNumber;
    private String email;
    private String passwordHash;
    private String passcodeHash;
    private String accountStatus;
    private LocalDateTime createdAt;
    private String role; // Danh sách vai trò cách nhau bởi dấu phẩy (e.g., ROLE_TAX_OFFICER,ROLE_CITIZEN)

    public Citizen() {
    }

    public Citizen(String cccdNumber, String firebaseUid, String fullName, LocalDate dob, String gender,
            String phoneNumber, String email, String passwordHash, String passcodeHash,
            String accountStatus, LocalDateTime createdAt, String role) {
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
        this.role = role;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasscodeHash() {
        return passcodeHash;
    }

    public void setPasscodeHash(String passcodeHash) {
        this.passcodeHash = passcodeHash;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static class CitizenBuilder {
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
        private String role;

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

        public CitizenBuilder dob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public CitizenBuilder gender(String gender) {
            this.gender = gender;
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

        public CitizenBuilder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public CitizenBuilder passcodeHash(String passcodeHash) {
            this.passcodeHash = passcodeHash;
            return this;
        }

        public CitizenBuilder accountStatus(String accountStatus) {
            this.accountStatus = accountStatus;
            return this;
        }

        public CitizenBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CitizenBuilder role(String role) {
            this.role = role;
            return this;
        }

        public Citizen build() {
            return new Citizen(cccdNumber, firebaseUid, fullName, dob, gender, phoneNumber, email,
                    passwordHash, passcodeHash, accountStatus, createdAt, role);
        }
    }
}
