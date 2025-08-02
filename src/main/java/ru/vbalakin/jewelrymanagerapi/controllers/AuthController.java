package ru.vbalakin.jewelrymanagerapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.vbalakin.jewelrymanagerapi.entities.UserEntity;
import ru.vbalakin.jewelrymanagerapi.exceptions.BadRequestException;
import ru.vbalakin.jewelrymanagerapi.repositories.UserRepository;

@RestController
@Transactional
@AllArgsConstructor
@Tag(name = "Authentication", description = "User registration and authentication")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "New User Registration")
    @PostMapping("/signup")
    public UserEntity signup(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false) String email) {

        if (userRepository.existsByUsername(username)) {
            throw new BadRequestException("Username already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);

        return userRepository.save(user);
    }

    @Operation(summary = "User authentication")
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Authentication successful";
    }
}
