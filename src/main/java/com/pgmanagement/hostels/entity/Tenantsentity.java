package com.pgmanagement.hostels.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Tenantsentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Roomentity room;

    @Column(nullable = false)
    private boolean rentPaid = false;  // default false

}