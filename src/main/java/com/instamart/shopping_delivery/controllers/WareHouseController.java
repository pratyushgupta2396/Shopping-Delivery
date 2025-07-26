package com.instamart.shopping_delivery.controllers;

import com.instamart.shopping_delivery.dto.WareHouseRegistrationDto;
import com.instamart.shopping_delivery.models.WareHouse;
import com.instamart.shopping_delivery.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WareHouseController {

    WareHouseService wareHouseService;

    @Autowired
    public WareHouseController(WareHouseService wareHouseService){
        this.wareHouseService = wareHouseService;
    }

    @PostMapping("/register")
    public ResponseEntity registerWareHouse(@RequestParam UUID userId,
                                            @RequestBody WareHouseRegistrationDto wareHouseRegistrationDto){
        // We are getting userId from request param & we are geetting wareHouseRegistrationDto in request body
        // Now we should call wareHouse Service to implement the logic
        WareHouse wareHouse = wareHouseService.registerWareHouse(userId, wareHouseRegistrationDto);
        return new ResponseEntity(wareHouse, HttpStatus.CREATED);
    }

    @PutMapping("/assign/manager")
    public ResponseEntity assignManagerToWareHouse(@RequestParam UUID adminId,
                                                   @RequestParam UUID wareHouseId,
                                                   @RequestParam UUID wareHouseAdminId){
        // WareHouse Service
        WareHouse wareHouse = wareHouseService.assignManagerToWareHouse(adminId,
                wareHouseId,
                wareHouseAdminId);
        return new ResponseEntity(wareHouse, HttpStatus.OK);

    }

}