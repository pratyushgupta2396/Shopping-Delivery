package com.instamart.shopping_delivery.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")

public class AppOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    int totalItems;
    int totalPrice;
    String status;
    @ManyToOne
    AppUser deliveryPartner;
    @ManyToOne
    AppUser shopper;
    String paymentType;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
