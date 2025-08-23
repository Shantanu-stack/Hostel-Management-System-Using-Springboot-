package com.pgmanagement.hostels.controller;

import com.pgmanagement.hostels.entity.HostelEntity;
import com.pgmanagement.hostels.service.HostelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostel")
public class HostelController {

    @Autowired
    private HostelService hs;

    @PostMapping("/add")
    public ResponseEntity<?> addHostel(@RequestBody HostelEntity hostel) {
        try {
            HostelEntity savedHostel = hs.saveHostel(hostel);
            return ResponseEntity.ok(savedHostel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<HostelEntity>> getAllHostels() {
        List<HostelEntity> allHostels = hs.getAllHostels();
        return ResponseEntity.ok(allHostels);
    }

    static class ErrorResponse {
        private String message;
        public ErrorResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}