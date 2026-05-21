package com.thanglong.vneid.domain.repository;

import com.thanglong.vneid.domain.model.OtpRequest;

import java.util.Optional;

/**
 * Cổng giao tiếp dữ liệu cho OtpRequest.
 */
public interface OtpRequestRepository {

    Optional<OtpRequest> findByEmailAndOtpCode(String email, String otpCode);

    Optional<OtpRequest> findByCccdNumberAndOtpCode(String cccdNumber, String otpCode);

    Optional<OtpRequest> findLatestByEmail(String email);

    OtpRequest save(OtpRequest otpRequest);

    void deleteByEmail(String email);

    void deleteByCccdNumber(String cccdNumber);
}
