package com.thanglong.vneid.usecase.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class SetPasswordRequest {
    @NotBlank(message = "Số CCCD không được để trống")
    @Pattern(regexp = "\\d{12}", message = "Số CCCD phải có đúng 12 chữ số")
    private String cccdNumber;
    
    @NotBlank(message = "Mật khẩu không được để trống")
    private String password;
    
    @NotBlank(message = "Mã PIN không được để trống")
    @Pattern(regexp = "\\d{6}", message = "Mã PIN phải có đúng 6 chữ số")
    private String passcode;

    public SetPasswordRequest() {}

    public SetPasswordRequest(String cccdNumber, String password, String passcode) {
        this.cccdNumber = cccdNumber;
        this.password = password;
        this.passcode = passcode;
    }

    public String getCccdNumber() { return cccdNumber; }
    public void setCccdNumber(String cccdNumber) { this.cccdNumber = cccdNumber; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPasscode() { return passcode; }
    public void setPasscode(String passcode) { this.passcode = passcode; }
}
