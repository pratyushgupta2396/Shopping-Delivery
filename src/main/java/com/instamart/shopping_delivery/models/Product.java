package com.instamart.shopping_delivery.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String productName;
    int unitPrice;
    int totalQuantity;
    String ownerCompany;
    String description;
    @ManyToOne
    AppUser createdBy;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}