package com.thanglong.vneid.usecase.dto;

import lombok.*;

import java.time.LocalDate;

/**
 * DTO trả về thông tin định danh công dân cho các service nội bộ.
 * Được sử dụng bởi Internal API: GET /api/vneid/internal/citizens/{cccd}
 */
public class CitizenIdentityDTO {

    private String cccdNumber;      // Số Căn cước công dân (12 số)
    private String fullName;
    private LocalDate dob;          // Ngày sinh
    private String gender;
    private String email;
    private String phoneNumber;

    public CitizenIdentityDTO() {}

    public CitizenIdentityDTO(String cccdNumber, String fullName, LocalDate dob, String gender, String email, String phoneNumber) {
        this.cccdNumber = cccdNumber;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static CitizenIdentityDTOBuilder builder() {
        return new CitizenIdentityDTOBuilder();
    }

    public String getCccdNumber() { return cccdNumber; }
    public void setCccdNumber(String cccdNumber) { this.cccdNumber = cccdNumber; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public static class CitizenIdentityDTOBuilder {
        private String cccdNumber;
        private String fullName;
        private LocalDate dob;
        private String gender;
        private String email;
        private String phoneNumber;

        public CitizenIdentityDTOBuilder cccdNumber(String cccdNumber) { this.cccdNumber = cccdNumber; return this; }
        public CitizenIdentityDTOBuilder fullName(String fullName) { this.fullName = fullName; return this; }
        public CitizenIdentityDTOBuilder dob(LocalDate dob) { this.dob = dob; return this; }
        public CitizenIdentityDTOBuilder gender(String gender) { this.gender = gender; return this; }
        public CitizenIdentityDTOBuilder email(String email) { this.email = email; return this; }
        public CitizenIdentityDTOBuilder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }
        public CitizenIdentityDTO build() {
            return new CitizenIdentityDTO(cccdNumber, fullName, dob, gender, email, phoneNumber);
        }
    }
}
