package com.pgmanagement.hostels.controller;

import com.pgmanagement.hostels.entity.Roomentity;
import com.pgmanagement.hostels.repository.RoomRepository;
import com.pgmanagement.hostels.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // ✅ Missing import added here
//@CrossOrigin(origins = "http://localhost:8080")

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService rs;

    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/addroom")
    public ResponseEntity<Roomentity> addRoom(@RequestBody Roomentity room) {
        Roomentity savedroom = rs.addRoom(room);
        return ResponseEntity.ok(savedroom);
    }

    @PatchMapping("/updateroom/{roomid}")
    public ResponseEntity<Roomentity> updateRoom(@PathVariable("roomid") Long id, @RequestBody Roomentity room) {
        Roomentity updatedroom = rs.updateRoom(id, room);
        return ResponseEntity.ok(updatedroom);
    }

    @GetMapping("/getroomstatus/{roomid}")
    public ResponseEntity<String> getRoomStatus(@PathVariable("roomid") Long roomId) {
        String status = rs.getRoomStatus(roomId);
        return ResponseEntity.ok(status);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Roomentity>> getAllRooms() {
        List<Roomentity> allRooms = roomRepository.findAll();
        return ResponseEntity.ok(allRooms);
    }
}
