package com.instamart.shopping_delivery.dto;

import com.instamart.shopping_delivery.models.Location;
import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WareHouseRegistrationDto {
    String wareHouseName;
    Location location;
}