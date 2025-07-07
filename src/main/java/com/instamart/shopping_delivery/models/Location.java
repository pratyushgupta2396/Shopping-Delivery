package com.instamart.shopping_delivery.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "locations")

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String address;
    String state;
    String country;
    String city;
    int pinCode;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
