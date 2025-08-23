package com.pgmanagement.hostels.controller;

import com.pgmanagement.hostels.entity.UserEntity;
import com.pgmanagement.hostels.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;

    public AuthController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // DTO
    public record UserReq(String username, String password) {}

    public static class Msg {
        private String message;
        public Msg(String m){ this.message = m; }
        public String getMessage(){ return message; }
        public void setMessage(String m){ this.message = m; }
    }

    public static class LoginRes extends Msg {
        private String username;
        public LoginRes(String msg, String username){ super(msg); this.username = username; }
        public String getUsername(){ return username; }
        public void setUsername(String u){ this.username = u; }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserReq req) {
        if (req == null || req.username() == null || req.username().isBlank()
                || req.password() == null || req.password().isBlank()) {
            return ResponseEntity.badRequest().body(new Msg("Username and password are required"));
        }
        if (userRepo.existsByUsername(req.username().trim())) {
            return ResponseEntity.badRequest().body(new Msg("Username already taken"));
        }
        UserEntity u = new UserEntity();
        u.setUsername(req.username().trim());
        u.setPassword(req.password()); // plain text by request
        userRepo.save(u);
        return ResponseEntity.ok(new Msg("Registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserReq req) {
        if (req == null) {
            return ResponseEntity.status(401).body(new Msg("Invalid username or password"));
        }
        return userRepo.findByUsername(req.username())
                .filter(u -> u.getPassword().equals(req.password()))
                .<ResponseEntity<?>>map(u -> ResponseEntity.ok(new LoginRes("Login successful", u.getUsername())))
                .orElseGet(() -> ResponseEntity.status(401).body(new Msg("Invalid username or password")));
    }
}
