package com.pgmanagement.hostels.service;
import com.pgmanagement.hostels.repository.HostelRepository;
import com.pgmanagement.hostels.entity.HostelEntity;
import com.pgmanagement.hostels.entity.Roomentity;
import com.pgmanagement.hostels.entity.Tenantsentity;
import com.pgmanagement.hostels.repository.RoomRepository;
import com.pgmanagement.hostels.repository.TenantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HostelRepository hostelRepository;

    @Autowired
    private TenantsRepository tenantsRepository;

    // Add a room to a hostel
    public Roomentity addRoom(Roomentity room) {
        if (room.getRoomType() == null) {
            throw new IllegalArgumentException("Room type must be specified");
        }
        if (room.getHostel() == null || room.getHostel().getHid() == null) {
            throw new IllegalArgumentException("Hostel must be specified with a valid ID");
        }

        // ✅ Fetch the hostel from DB to ensure it's managed by JPA
        HostelEntity hostel = hostelRepository.findById(room.getHostel().getHid())
                .orElseThrow(() -> new RuntimeException("Hostel not found with ID: " + room.getHostel().getHid()));

        room.setHostel(hostel); // Ensure the room has a managed hostel reference

        // Set initial occupancy if null
        if (room.getCurrentOccupancy() == null) {
            room.setCurrentOccupancy(0);
        }

        // Validate occupancy against capacity
        if (room.getCurrentOccupancy() < 0 || room.getCurrentOccupancy() > room.getCapacity()) {
            throw new IllegalArgumentException("Initial occupancy must be between 0 and " + room.getCapacity());
        }

        return roomRepository.save(room);
    }


    // Update room details
    public Roomentity updateRoom(Long roomId, Roomentity updatedRoom) {
        Roomentity existingRoom = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // Update room type if provided
        if (updatedRoom.getRoomType() != null) {
            existingRoom.setRoomType(updatedRoom.getRoomType());
        }

        // Update current occupancy based on tenants in the room
        List<Tenantsentity> tenants = tenantsRepository.findByRoom(existingRoom);
        existingRoom.setCurrentOccupancy(tenants.size());

        // Validate that current occupancy doesn't exceed capacity
        if (existingRoom.getCurrentOccupancy() > existingRoom.getCapacity()) {
            throw new IllegalStateException("Current occupancy (" + existingRoom.getCurrentOccupancy() +
                    ") exceeds room capacity (" + existingRoom.getCapacity() + ")");
        }

        return roomRepository.save(existingRoom);
    }

    // Get room status (e.g., "Vacant", "Partially Occupied", "Fully Occupied")
    public String getRoomStatus(Long roomId) {
        Roomentity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        if (room.getCurrentOccupancy() == 0) {
            return "Vacant";
        } else if (room.getCurrentOccupancy() < room.getCapacity()) {
            return "Partially Occupied";
        } else {
            return "Fully Occupied";
        }
    }
}