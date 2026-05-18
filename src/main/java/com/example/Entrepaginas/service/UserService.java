package com.example.Entrepaginas.service;

import com.example.Entrepaginas.dto.LoginRequestDto;
import com.example.Entrepaginas.dto.LoginResponseDto;
import com.example.Entrepaginas.dto.RegisterRequestDto;
import com.example.Entrepaginas.model.Role;
import com.example.Entrepaginas.model.User;
import com.example.Entrepaginas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDto register(RegisterRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new LoginResponseDto(token, user.getUsername(), user.getRole().name());
    }

    public LoginResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));
        String token = jwtService.generateToken(user);
        return new LoginResponseDto(token, user.getUsername(), user.getRole().name());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}

