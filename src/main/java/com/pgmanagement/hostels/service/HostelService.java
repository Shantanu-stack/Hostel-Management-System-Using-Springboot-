package com.pgmanagement.hostels.service;

import com.pgmanagement.hostels.entity.HostelEntity;
import com.pgmanagement.hostels.entity.Roomentity;
import com.pgmanagement.hostels.repository.HostelRepository;
import com.pgmanagement.hostels.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HostelService {

 @Autowired
 private HostelRepository hostelRepo;

 @Autowired
 private RoomRepository roomRepository;

 public HostelEntity saveHostel(HostelEntity hostel) {
  return hostelRepo.save(hostel);
 }

 @Transactional(readOnly = true)
 public List<HostelEntity> getAllHostels() {
  List<HostelEntity> hostels = hostelRepo.findAll();
  hostels.forEach(hostel -> {
   List<Roomentity> rooms = roomRepository.findByHostel(hostel);
   long totalRooms = rooms.size();
   hostel.setTotalRooms(totalRooms); // Set transient field
   if (totalRooms == 0) {
    hostel.setStatus("No Rooms");
   } else {
    long vacantRooms = rooms.stream().filter(r -> r.getCurrentOccupancy() == 0).count();
    long fullRooms = rooms.stream().filter(r -> r.getCurrentOccupancy() >= r.getCapacity()).count();
    hostel.setStatus(vacantRooms == totalRooms ? "All Vacant" :
            fullRooms == totalRooms ? "All Full" : "Partially Occupied");
   }
  });
  return hostels;
 }
}