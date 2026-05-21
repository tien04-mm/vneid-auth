package com.thanglong.vneid.infrastructure.adapter.persistence.jpa;

import com.thanglong.vneid.infrastructure.adapter.persistence.entity.OtpRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRequestJpaRepository extends JpaRepository<OtpRequestEntity, Long> {

    Optional<OtpRequestEntity> findByEmailAndOtpCode(String email, String otpCode);

    Optional<OtpRequestEntity> findByCitizen_CccdNumberAndOtpCode(String cccdNumber, String otpCode);

    @Query("SELECT o FROM OtpRequestEntity o WHERE o.email = :email ORDER BY o.createdAt DESC LIMIT 1")
    Optional<OtpRequestEntity> findLatestByEmail(String email);

    void deleteByEmail(String email);

    void deleteByCitizen_CccdNumber(String cccdNumber);
}
