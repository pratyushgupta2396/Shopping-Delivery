package com.instamart.shopping_delivery.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "orderitems")

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    UUID orderId;
    UUID productId;
    int quantity;
    int totalAmount;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
