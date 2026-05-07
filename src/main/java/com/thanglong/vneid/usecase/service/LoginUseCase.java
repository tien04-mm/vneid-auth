package com.thanglong.vneid.usecase.service;

import com.thanglong.vneid.domain.model.Citizen;
import com.thanglong.vneid.domain.repository.CitizenRepository;
import com.thanglong.vneid.usecase.dto.AuthResponse;
import com.thanglong.vneid.usecase.port.JwtProvider;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginUseCase {

    private final CitizenRepository citizenRepository;
    private final JwtProvider jwtProvider;

    public LoginUseCase(CitizenRepository citizenRepository, JwtProvider jwtProvider) {
        this.citizenRepository = citizenRepository;
        this.jwtProvider = jwtProvider;
    }

    public AuthResponse login(String cccdNumber, String password) {
        Citizen citizen = citizenRepository.findByCccdNumber(cccdNumber)
                .orElseThrow(() -> new RuntimeException("Số CCCD không tồn tại"));

        // Trong thực tế cần check password hash
        if (!citizen.getPassword().equals(password)) {
            throw new RuntimeException("Mật khẩu không chính xác");
        }

        if (!"ACTIVE".equals(citizen.getAccountStatus())) {
            throw new RuntimeException("Tài khoản chưa được kích hoạt");
        }

        List<String> roles = parseRoles(citizen.getRole());
        String activeRole = roles.get(0); // Mặc định role đầu tiên là active

        String token = jwtProvider.generateToken(
                citizen.getCccdNumber(),
                citizen.getEmail(),
                activeRole,
                roles
        );

        return AuthResponse.builder()
                .token(token)
                .tokenType("Bearer")
                .userId(citizen.getCccdNumber())
                .fullName(citizen.getFullName())
                .email(citizen.getEmail())
                .activeRole(activeRole)
                .roles(roles)
                .message("Đăng nhập thành công")
                .build();
    }

    public AuthResponse switchRole(String token, String targetRole) {
        if (!jwtProvider.validateToken(token)) {
            throw new RuntimeException("Token không hợp lệ hoặc đã hết hạn");
        }

        String cccdNumber = jwtProvider.extractCccd(token);
        String email = jwtProvider.extractEmail(token);
        List<String> roles = jwtProvider.extractRoles(token);

        if (!roles.contains(targetRole)) {
            throw new RuntimeException("Bạn không có quyền chuyển sang vai trò: " + targetRole);
        }

        // Cấp token mới với activeRole mới
        String newToken = jwtProvider.generateToken(cccdNumber, email, targetRole, roles);

        Citizen citizen = citizenRepository.findByCccdNumber(cccdNumber)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông tin công dân"));

        return AuthResponse.builder()
                .token(newToken)
                .tokenType("Bearer")
                .userId(cccdNumber)
                .fullName(citizen.getFullName())
                .email(email)
                .activeRole(targetRole)
                .roles(roles)
                .message("Chuyển vai trò thành công")
                .build();
    }

    private List<String> parseRoles(String roleString) {
        if (roleString == null || roleString.isEmpty()) {
            return List.of("ROLE_CITIZEN");
        }
        return Arrays.stream(roleString.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
