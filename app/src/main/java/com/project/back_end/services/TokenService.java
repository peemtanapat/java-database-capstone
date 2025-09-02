package com.project.back_end.services;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.project.back_end.models.Admin;
import com.project.back_end.models.Doctor;
import com.project.back_end.models.Patient;
import com.project.back_end.models.Role;
import com.project.back_end.repo.AdminRepository;
import com.project.back_end.repo.DoctorRepository;
import com.project.back_end.repo.PatientRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class TokenService {

    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationMs;

    public String generateToken(String email, Role role) {
        return Jwts.builder()
                .claim("role", role)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] decode = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(decode);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> getterFunction) {
        return getterFunction.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, Role expectedRole) {
        if (expectedRole == Role.ADMIN) {
            Admin admin = adminRepository.findByEmail(extractEmail(token));
            if (admin == null) {
                return false;
            }
            return admin.getUserAccount().isActive();
        } else if (expectedRole == Role.DOCTOR) {
            Doctor doctor = doctorRepository.findByEmail(extractEmail(token));
            if (doctor == null) {
                return false;
            }
            return doctor.getUserAccount().isActive();
        } else if (expectedRole == Role.PATIENT) {
            Patient patient = patientRepository.findByEmail(extractEmail(token));
            if (patient == null) {
                return false;
            }
            return patient.getUserAccount().isActive();
        }
        return false;
    }
}
