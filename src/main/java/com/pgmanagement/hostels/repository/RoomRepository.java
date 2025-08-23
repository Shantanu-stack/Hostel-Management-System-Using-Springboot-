package com.pgmanagement.hostels.repository;

import com.pgmanagement.hostels.entity.HostelEntity;
import com.pgmanagement.hostels.entity.Roomentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Roomentity, Long> {
    List<Roomentity> findByHostel(HostelEntity hostel);
}