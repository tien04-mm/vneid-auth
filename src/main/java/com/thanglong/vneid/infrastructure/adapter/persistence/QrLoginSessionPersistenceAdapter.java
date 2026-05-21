package com.thanglong.vneid.infrastructure.adapter.persistence;

import com.thanglong.vneid.domain.model.QrLoginSession;
import com.thanglong.vneid.domain.repository.QrLoginSessionRepository;
import com.thanglong.vneid.infrastructure.adapter.persistence.entity.QrLoginSessionEntity;
import com.thanglong.vneid.infrastructure.adapter.persistence.entity.CitizenEntity;
import com.thanglong.vneid.infrastructure.adapter.persistence.jpa.QrLoginSessionJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QrLoginSessionPersistenceAdapter implements QrLoginSessionRepository {

    private final QrLoginSessionJpaRepository jpaRepository;

    public QrLoginSessionPersistenceAdapter(QrLoginSessionJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<QrLoginSession> findByQrToken(String qrToken) {
        return jpaRepository.findByQrToken(qrToken).map(this::toDomain);
    }

    @Override
    public QrLoginSession save(QrLoginSession qrLoginSession) {
        QrLoginSessionEntity entity = toEntity(qrLoginSession);
        QrLoginSessionEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public void deleteByQrToken(String qrToken) {
        jpaRepository.deleteByQrToken(qrToken);
    }

    @Override
    public void deleteExpiredSessions() {
        jpaRepository.deleteExpiredSessions();
    }

    private QrLoginSession toDomain(QrLoginSessionEntity entity) {
        if (entity == null) return null;
        return QrLoginSession.builder()
                .qrToken(entity.getQrToken())
                .status(entity.getStatus())
                .cccdNumber(entity.getCitizen() != null ? entity.getCitizen().getCccdNumber() : null)
                .createdAt(entity.getCreatedAt())
                .expiresAt(entity.getExpiresAt())
                .build();
    }

    private QrLoginSessionEntity toEntity(QrLoginSession domain) {
        if (domain == null) return null;
        CitizenEntity citizen = null;
        if (domain.getCccdNumber() != null) {
            citizen = new CitizenEntity();
            citizen.setCccdNumber(domain.getCccdNumber());
        }
        return QrLoginSessionEntity.builder()
                .qrToken(domain.getQrToken())
                .status(domain.getStatus())
                .citizen(citizen)
                .createdAt(domain.getCreatedAt())
                .expiresAt(domain.getExpiresAt())
                .build();
    }
}
