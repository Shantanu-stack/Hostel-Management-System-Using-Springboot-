package com.pgmanagement.hostels.service;

import com.pgmanagement.hostels.entity.Roomentity;
import com.pgmanagement.hostels.entity.Tenantsentity;
import com.pgmanagement.hostels.repository.RoomRepository;
import com.pgmanagement.hostels.repository.TenantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TenantService {

    @Autowired
    private TenantsRepository tenantRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Tenantsentity addTenant(Tenantsentity tenant) {
        Roomentity room = roomRepository.findById(tenant.getRoom().getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        List<Tenantsentity> tenants = tenantRepository.findByRoom(room);
        if (tenants.size() >= room.getCapacity()) {
            throw new IllegalStateException("Room is already at full capacity (" + room.getCapacity() + ")");
        }
        tenant.setRoom(room);
        Tenantsentity savedTenant = tenantRepository.save(tenant);
        room.setCurrentOccupancy(tenants.size() + 1);
        roomRepository.save(room);
        return tenantRepository.findById(savedTenant.getId()).orElse(savedTenant);
    }

    public Tenantsentity updateTenant(Long tenantId, Tenantsentity updatedTenant) {
        Tenantsentity existingTenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
        if (updatedTenant.getName() != null) {
            existingTenant.setName(updatedTenant.getName());
        }
        if (updatedTenant.getAge() != null) {
            existingTenant.setAge(updatedTenant.getAge());
        }
        if (updatedTenant.getGender() != null) {
            existingTenant.setGender(updatedTenant.getGender());
        }
        if (updatedTenant.getRoom() != null && !updatedTenant.getRoom().equals(existingTenant.getRoom())) {
            Roomentity oldRoom = existingTenant.getRoom();
            Roomentity newRoom = roomRepository.findById(updatedTenant.getRoom().getId())
                    .orElseThrow(() -> new RuntimeException("New room not found"));
            List<Tenantsentity> newRoomTenants = tenantRepository.findByRoom(newRoom);
            if (newRoomTenants.size() >= newRoom.getCapacity()) {
                throw new IllegalStateException("New room is already at full capacity (" + newRoom.getCapacity() + ")");
            }
            existingTenant.setRoom(newRoom);
            if (oldRoom != null) {
                List<Tenantsentity> oldRoomTenants = tenantRepository.findByRoom(oldRoom);
                oldRoom.setCurrentOccupancy(oldRoomTenants.size() - 1);
                roomRepository.save(oldRoom);
            }
            newRoom.setCurrentOccupancy(newRoomTenants.size() + 1);
            roomRepository.save(newRoom);
        }
        return tenantRepository.save(existingTenant);
    }

    public Tenantsentity getTenantById(Long tenantId) {
        return tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
    }

    @Transactional(readOnly = true)
    public List<Tenantsentity> getAllTenants() {
        List<Tenantsentity> tenants = tenantRepository.findAll();
        tenants.forEach(tenant -> {
            Roomentity room = tenant.getRoom();
            if (room != null) {
                room.getHostel();
            }
        });
        return tenants;
    }

    @Transactional
    public void deleteTenant(Long tenantId) {
        Tenantsentity tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));
        Roomentity room = tenant.getRoom();
        tenantRepository.deleteById(tenantId);
        if (room != null) {
            List<Tenantsentity> remainingTenants = tenantRepository.findByRoom(room);
            room.setCurrentOccupancy(remainingTenants.size());
            roomRepository.save(room);
        }
    }
}