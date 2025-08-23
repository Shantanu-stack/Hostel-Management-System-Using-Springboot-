package com.pgmanagement.hostels.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    // Keeping it simple as requested (plain text). For real apps, hash this.
    @ToString.Exclude
    @Column(nullable = false, length = 100)
    private String password;
}
