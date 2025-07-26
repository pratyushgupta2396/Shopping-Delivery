package com.instamart.shopping_delivery.dto;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
public class ProductDto {
    UUID id;
    String productName;
    int unitPrice;
    int totalQuantity;
    String ownerCompany;
    String description;
}