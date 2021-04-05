package com.vas.aos.presentation.api;

import com.vas.aos.core.application.dtos.AuthRequestDTO;
import com.vas.aos.core.domain.entities.User;
import com.vas.aos.infraestructure.spring.filters.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users-management/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            log.info("User {} trying auth", authRequestDTO);
            log.info("password {}", passwordEncoder.encode(authRequestDTO.password()));
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.username(),
                            authRequestDTO.password())
            );

            User user = (User) authentication.getPrincipal();

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION,
                    jwtTokenUtil.generateAccessToken(user)).body("Success");
        } catch (BadCredentialsException ex) {
            log.error("Error at auth", ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}