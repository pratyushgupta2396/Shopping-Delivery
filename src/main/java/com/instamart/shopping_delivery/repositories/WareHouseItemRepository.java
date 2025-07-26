package com.instamart.shopping_delivery.repositories;

import com.instamart.shopping_delivery.models.WareHouseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WareHouseItemRepository extends JpaRepository<WareHouseItem, UUID> {
}