package com.pgmanagement.hostels.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Roomentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hostel_id")
    private HostelEntity hostel;

    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    private Integer currentOccupancy;

    @Transient
    public Integer getCapacity() {
        return roomType != null ? roomType.getCapacity() : 0; // Null check
    }

    public enum RoomType {
        SINGLE(1), DOUBLE_SHARED(2), TRIPLE_SHARED(3);

        private final int capacity;

        RoomType(int capacity) {
            this.capacity = capacity;
        }

        public int getCapacity() {
            return capacity;
        }
    }
}