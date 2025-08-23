package com.pgmanagement.hostels.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class HostelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hid;

    @Column(name = "hostel_name")
    private String name;

    @Column(name = "Hostel_owner")
    private String owner;

    @Column(name = "floors")
    private Integer floors;

    @Transient
    private Long totalRooms;

    @Transient
    private String status;
}