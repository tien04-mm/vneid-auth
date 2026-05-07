package com.thanglong.vneid.usecase.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO cho yêu cầu xác nhận QR từ thiết bị mobile.
 */
public class QrConfirmRequest {

    @NotBlank(message = "Mã QR không được để trống")
    private String qrToken;

    @com.fasterxml.jackson.annotation.JsonProperty("firebase_uid")
    @NotBlank(message = "Firebase UID không được để trống")
    private String firebaseUid;

    public QrConfirmRequest() {}
    public QrConfirmRequest(String qrToken, String firebaseUid) {
        this.qrToken = qrToken;
        this.firebaseUid = firebaseUid;
    }
    public String getQrToken() { return qrToken; }
    public void setQrToken(String qrToken) { this.qrToken = qrToken; }
    public String getFirebaseUid() { return firebaseUid; }
    public void setFirebaseUid(String firebaseUid) { this.firebaseUid = firebaseUid; }
}
