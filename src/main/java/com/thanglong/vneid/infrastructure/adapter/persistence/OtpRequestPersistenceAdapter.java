package com.thanglong.vneid.infrastructure.adapter.persistence;

import com.thanglong.vneid.domain.model.OtpRequest;
import com.thanglong.vneid.domain.repository.OtpRequestRepository;
import com.thanglong.vneid.infrastructure.adapter.persistence.entity.OtpRequestEntity;
import com.thanglong.vneid.infrastructure.adapter.persistence.jpa.OtpRequestJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OtpRequestPersistenceAdapter implements OtpRequestRepository {

    private final OtpRequestJpaRepository otpRequestJpaRepository;

    public OtpRequestPersistenceAdapter(OtpRequestJpaRepository otpRequestJpaRepository) {
        this.otpRequestJpaRepository = otpRequestJpaRepository;
    }

    @Override
    public Optional<OtpRequest> findByEmailAndOtpCode(String email, String otpCode) {
        return otpRequestJpaRepository.findByEmailAndOtpCode(email, otpCode)
                .map(this::toDomainModel);
    }

    @Override
    public Optional<OtpRequest> findByCccdNumberAndOtpCode(String cccdNumber, String otpCode) {
        return otpRequestJpaRepository.findByCccdNumberAndOtpCode(cccdNumber, otpCode)
                .map(this::toDomainModel);
    }

    @Override
    public Optional<OtpRequest> findLatestByEmail(String email) {
        return otpRequestJpaRepository.findLatestByEmail(email)
                .map(this::toDomainModel);
    }

    @Override
    public OtpRequest save(OtpRequest otpRequest) {
        OtpRequestEntity entity = toEntity(otpRequest);
        OtpRequestEntity savedEntity = otpRequestJpaRepository.save(entity);
        return toDomainModel(savedEntity);
    }

    @Override
    public void deleteByEmail(String email) {
        otpRequestJpaRepository.deleteByEmail(email);
    }

    @Override
    public void deleteByCccdNumber(String cccdNumber) {
        otpRequestJpaRepository.deleteByCccdNumber(cccdNumber);
    }

    private OtpRequest toDomainModel(OtpRequestEntity entity) {
        if (entity == null) return null;
        return OtpRequest.builder()
                .id(entity.getId())
                .cccdNumber(entity.getCccdNumber())
                .email(entity.getEmail())
                .otpCode(entity.getOtpCode())
                .createdAt(entity.getCreatedAt())
                .expiresAt(entity.getExpiresAt())
                .used(entity.isUsed())
                .build();
    }

    private OtpRequestEntity toEntity(OtpRequest otpRequest) {
        if (otpRequest == null) return null;
        return OtpRequestEntity.builder()
                .id(otpRequest.getId())
                .cccdNumber(otpRequest.getCccdNumber())
                .email(otpRequest.getEmail())
                .otpCode(otpRequest.getOtpCode())
                .createdAt(otpRequest.getCreatedAt())
                .expiresAt(otpRequest.getExpiresAt())
                .used(otpRequest.isUsed())
                .build();
    }
}
