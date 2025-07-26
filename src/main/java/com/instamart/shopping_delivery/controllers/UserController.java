package com.instamart.shopping_delivery.controllers;

import com.instamart.shopping_delivery.exceptions.InvalidOperationException;
import com.instamart.shopping_delivery.exceptions.UserNotExistException;
import com.instamart.shopping_delivery.models.AppUser;
import com.instamart.shopping_delivery.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    AppUserService appUserService;

    @Autowired
    public UserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    @PostMapping("/customer/registration")
    public AppUser customerRegistration(@RequestBody AppUser customer){
        System.out.println(customer);
        // CustomerService
        AppUser user = appUserService.registerCustomer(customer);
        return user;
    }

    @PostMapping("/warehouse/admin/invite")
    public ResponseEntity wareHouseAdminInvite(@RequestParam UUID userId,
                                               @RequestBody AppUser wareHouseAdmin){
        // appuserservice -> Warehouse admin invite
        try{
            appUserService.wareHouseAdminInvite(userId, wareHouseAdmin);
            return new ResponseEntity("Inactive record created inside users table.", HttpStatus.CREATED);
        }catch (InvalidOperationException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }catch (UserNotExistException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/warehouse/admin/accept/invite/{wareHouseAdminId}")
    public void acceptWareHouseAdminInvite(@PathVariable UUID wareHouseAdminId){
        appUserService.acceptWareHouseAdminInvite(wareHouseAdminId);
    }



}