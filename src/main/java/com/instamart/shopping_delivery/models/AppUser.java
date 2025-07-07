package com.instamart.shopping_delivery.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Data
@Entity
@Table(name = "users")

public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    @Column(unique = true, nullable = false)
    String email;
    @Column(nullable = false)
    String password;
    @Column(unique = true, nullable = false)
    Long phoneNumber;
    String userType;
    String status;
    @OneToMany
    List<Location> locations;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
