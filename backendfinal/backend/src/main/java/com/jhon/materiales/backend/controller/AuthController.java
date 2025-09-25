package com.jhon.materiales.backend.controller;

import com.jhon.materiales.backend.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // 🔹 DTO simple para login
    public record LoginRequest(String username, String password) {}

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        Map<String, String> response = new HashMap<>();

        // Validación HARDCODEADA (solo para prueba)
        if ("sa".equals(request.username()) && "12345".equals(request.password())) {
            String token = jwtUtil.generarToken(request.username());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    // 🔹 Endpoint público de ping
    @GetMapping("/ping")
    public Map<String, String> ping() {
        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Auth funcionando");
        return response;
    }
}
