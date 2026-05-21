package com.thanglong.vneid.usecase.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.thanglong.vneid.domain.model.Citizen;
import com.thanglong.vneid.domain.model.QrLoginSession;
import com.thanglong.vneid.domain.repository.CitizenRepository;
import com.thanglong.vneid.domain.repository.QrLoginSessionRepository;
import com.thanglong.vneid.usecase.dto.*;
import com.thanglong.vneid.usecase.port.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

/**
 * Use case xử lý đăng nhập bằng mã QR.
 */
@Service
public class QrAuthUseCase {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(QrAuthUseCase.class);

    private final QrLoginSessionRepository qrSessionRepository;
    private final CitizenRepository citizenRepository;
    private final JwtProvider jwtProvider;

    public QrAuthUseCase(QrLoginSessionRepository qrSessionRepository, CitizenRepository citizenRepository,
            JwtProvider jwtProvider) {
        this.qrSessionRepository = qrSessionRepository;
        this.citizenRepository = citizenRepository;
        this.jwtProvider = jwtProvider;
    }

    /**
     * Khởi tạo mã QR để đăng nhập.
     */
    @Transactional
    public QrGenerateResponse generateQr() {
        String qrToken = UUID.randomUUID().toString();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5);

        QrLoginSession session = QrLoginSession.builder()
                .qrToken(qrToken)
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .expiresAt(expiresAt)
                .build();

        qrSessionRepository.save(session);
        log.info("Khởi tạo mã QR thành công: {}", qrToken);

        // Sinh Base64 QR Code
        String base64QrCode = generateBase64QrCode(qrToken);

        return QrGenerateResponse.builder()
                .qrToken(qrToken)
                .qrBase64Image(base64QrCode)
                .expiresAt(expiresAt)
                .message("Khởi tạo mã QR thành công")
                .build();
    }

    /**
     * Kiểm tra trạng thái của mã QR (được gọi từ web frontend).
     */
    public ApiResponse<QrStatusResponse> getQrStatus(String qrToken) {
        QrLoginSession session = qrSessionRepository.findByQrToken(qrToken)
                .orElseThrow(() -> new RuntimeException("Mã QR không tồn tại"));

        // Kiểm tra hết hạn
        if (session.getExpiresAt().isBefore(LocalDateTime.now()) && !"SUCCESS".equals(session.getStatus())) {
            session.setStatus("EXPIRED");
            qrSessionRepository.save(session);
        }

        if ("EXPIRED".equals(session.getStatus())) {
            return ApiResponse.error("Mã QR đã hết hạn", "EXPIRED");
        }

        QrStatusResponse response = new QrStatusResponse(session.getStatus(), null);
        return ApiResponse.success(response, session.getStatus());
    }

    /**
     * Handshake cuối cùng: Đăng nhập bằng QR sau khi Mobile đã xác nhận.
     */
    public ApiResponse<AuthResponse> loginByQr(String qrToken) {
        QrLoginSession session = qrSessionRepository.findByQrToken(qrToken)
                .orElseThrow(() -> new RuntimeException("Mã QR không tồn tại"));

        if (!"SUCCESS".equals(session.getStatus())) {
            throw new RuntimeException("Mã QR chưa được xác nhận hoặc đã hết hạn");
        }

        // Sinh JWT Token cho công dân đã xác nhận QR
        Citizen citizen = citizenRepository.findByCccdNumber(session.getCccdNumber())
                .orElseThrow(() -> new RuntimeException("Công dân không tồn tại với CCCD: " + session.getCccdNumber()));

        java.util.List<String> roles = java.util.List.of("ROLE_CITIZEN");
        String activeRole = "ROLE_CITIZEN";
        String fullName = citizen.getFullName() != null ? citizen.getFullName() : "Người dùng";
        String email = citizen.getEmail() != null ? citizen.getEmail() : "";

        String token = jwtProvider.generateToken(
                citizen.getCccdNumber(),
                email,
                activeRole,
                roles);

        log.info("Handshake QR thành công cho CCCD: {}", citizen.getCccdNumber());

        AuthResponse authResponse = AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .userId(citizen.getCccdNumber())
                .fullName(fullName)
                .email(email)
                .activeRole(activeRole)
                .roles(roles)
                .message("SUCCESS")
                .build();

        return ApiResponse.success(authResponse, "Đăng nhập thành công");
    }

    /**
     * Xác nhận đăng nhập từ thiết bị Mobile.
     */
    @Transactional
    public void confirmQr(QrConfirmRequest request) {
        QrLoginSession session = qrSessionRepository.findByQrToken(request.getQrToken())
                .orElseThrow(() -> new RuntimeException("Mã QR không hợp lệ"));

        if ("EXPIRED".equals(session.getStatus())) {
            throw new RuntimeException("EXPIRED");
        }

        if (!"PENDING".equals(session.getStatus())) {
            throw new RuntimeException("Mã QR đã được sử dụng hoặc không hợp lệ");
        }

        if (session.getExpiresAt().isBefore(LocalDateTime.now())) {
            session.setStatus("EXPIRED");
            qrSessionRepository.save(session);
            throw new RuntimeException("EXPIRED");
        }

        // Tìm CCCD thông qua Firebase UID
        Citizen citizen = citizenRepository.findByFirebaseUid(request.getFirebaseUid())
                .orElseThrow(() -> new RuntimeException(
                        "Tài khoản chưa được liên kết với VNeID (Không tìm thấy Firebase UID)"));

        // Cập nhật trạng thái và lưu thông tin công dân
        session.setStatus("SUCCESS");
        session.setCccdNumber(citizen.getCccdNumber());

        qrSessionRepository.save(session);
        log.info("Xác nhận mã QR thành công từ mobile, Firebase UID: {}, CCCD: {}", request.getFirebaseUid(),
                citizen.getCccdNumber());
    }

    private String generateBase64QrCode(String qrToken) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(qrToken, BarcodeFormat.QR_CODE, 250, 250);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(pngData);
        } catch (Exception e) {
            log.error("Lỗi khi sinh mã QR", e);
            return null;
        }
    }
}
