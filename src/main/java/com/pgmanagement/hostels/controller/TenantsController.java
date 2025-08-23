package com.pgmanagement.hostels.controller;

import com.pgmanagement.hostels.entity.Tenantsentity;
import com.pgmanagement.hostels.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantsController {

    @Autowired
    private TenantService ts;

    @PostMapping("/addtenant")
    public ResponseEntity<?> addTenants(@RequestBody Tenantsentity tenants) {
        try {
            Tenantsentity savedTenant = ts.addTenant(tenants);
            return ResponseEntity.ok(savedTenant);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PatchMapping("/updatetenant/{tenantid}")
    public ResponseEntity<?> updateTenant(@PathVariable Long tenantid, @RequestBody Tenantsentity tenants) {
        try {
            Tenantsentity updatedTenant = ts.updateTenant(tenantid, tenants);
            return ResponseEntity.ok(updatedTenant);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/tenantbyid/{tenantid}")
    public ResponseEntity<?> getTenantById(@PathVariable Long tenantid) {
        try {
            Tenantsentity tenant = ts.getTenantById(tenantid);
            return ResponseEntity.ok(tenant);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tenantsentity>> getAllTenants() {
        List<Tenantsentity> allTenants = ts.getAllTenants();
        return ResponseEntity.ok(allTenants);
    }

    @DeleteMapping("/{tenantid}")
    public ResponseEntity<?> deleteTenant(@PathVariable Long tenantid) {
        try {
            ts.deleteTenant(tenantid);
            return ResponseEntity.ok(new SuccessResponse("Tenant deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ErrorResponse(e.getMessage()));
        }
    }

    static class ErrorResponse {
        private String message;
        public ErrorResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }

    static class SuccessResponse {
        private String message;
        public SuccessResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}