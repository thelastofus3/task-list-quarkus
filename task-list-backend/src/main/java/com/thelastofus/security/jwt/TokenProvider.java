package com.thelastofus.security.jwt;

import com.thelastofus.dto.jwt.JwtResponse;
import com.thelastofus.model.Role;
import com.thelastofus.model.User;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.security.PrivateKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenProvider {

    JwtService jwtService;

    JwtProperties jwtProperties;

    public JwtResponse generateToken(User user) {
        String accessToken = createAccessToken(user.getUsername(), user.getRole());
        return JwtResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .accessToken(accessToken)
                .build();
    }

    private String createAccessToken(String username, Role role) {
        PrivateKey privateKey = jwtService.readPrivateKey(jwtProperties.secret());

        Instant validity = Instant.now();

        return Jwt.claims()
                .issuer(jwtProperties.issuer())
                .subject(username)
                .issuedAt(validity)
                .expiresAt(validity.plus(jwtProperties.access(), ChronoUnit.HOURS))
                .groups(String.valueOf(role))
                .jws()
                .keyId(jwtProperties.secret())
                .sign(privateKey);
    }
}
