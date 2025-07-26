package com.instamart.shopping_delivery.service;

import com.instamart.shopping_delivery.dto.WareHouseRegistrationDto;
import com.instamart.shopping_delivery.exceptions.InvalidOperationException;
import com.instamart.shopping_delivery.models.*;
import com.instamart.shopping_delivery.repositories.WareHouseItemRepository;
import com.instamart.shopping_delivery.repositories.WareHouseRepository;
import com.instamart.shopping_delivery.utility.MappingUtility;
import jakarta.servlet.http.PushBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class WareHouseService {

    AppUserService appUserService;
    WareHouseRepository wareHouseRepository;
    LocationService locationService;
    MailService mailService;
    MappingUtility mappingUtility;
    ProductService productService;
    WareHouseItemRepository wareHouseItemRepository;
    @Autowired
    public WareHouseService(AppUserService appUserService,
                            WareHouseRepository wareHouseRepository,
                            LocationService locationService,
                            MappingUtility mappingUtility,
                            MailService mailService,
                            ProductService productService,
                            WareHouseItemRepository wareHouseItemRepository){
        this.appUserService = appUserService;
        this.wareHouseRepository = wareHouseRepository;
        this.locationService = locationService;
        this.mappingUtility = mappingUtility;
        this.mailService = mailService;
        this.productService = productService;
        this.wareHouseItemRepository = wareHouseItemRepository;
    }

    public WareHouse saveWareHouse(WareHouse wareHouse){
        return this.wareHouseRepository.save(wareHouse);
    }

    public WareHouse registerWareHouse(UUID userId,
                                       WareHouseRegistrationDto wareHouseRegistrationDto){
        // 1. Validate the Id belongs to app admin or not
        // So, we should what ?
        AppUser admin  = appUserService.isAppAdmin(userId);

        if(admin == null){
            throw new InvalidOperationException(String.format("User with id %s not allowed to register ware house", userId.toString()));
        }
        Location location = locationService.createLocation(wareHouseRegistrationDto.getLocation());
        // 2. Map details of wareHouse Registration DTO to WareHouse model.
        WareHouse wareHouse = mappingUtility.mapWareHouseToDtoToModel(wareHouseRegistrationDto, location);
        // 3. call Warehouse Repository to save warehouse in the warehouse table.
        wareHouse = this.saveWareHouse(wareHouse);
        // Notify Application admin that new warehouse got registered in your application.
        // We need mail service ->
        mailService.sendCreateWareHouseMail(wareHouse, admin);

        return wareHouse;
    }

    public WareHouse getWareHouseById(UUID wid){
        return wareHouseRepository.findById(wid).orElse(null);
    }

    public WareHouseItem assignProductToWareHouse(WareHouseItem wareHouseItem){
        UUID wid = wareHouseItem.getWid();
        UUID pid = wareHouseItem.getPid();
        // Validate both the ids are correct or not ?
        Product product = productService.isValidProduct(pid);
        int totalQuantity = product.getTotalQuantity();
        if(totalQuantity < wareHouseItem.getQuantity()){
            throw new InvalidOperationException("Can't assign quantity to warehouse as the requested quantity is mpre then the actual qunatity");
        }
        product.setTotalQuantity(totalQuantity - wareHouseItem.getQuantity());
        productService.updateProduct(product);
        wareHouseItem.setCreatedAt(LocalDateTime.now());
        wareHouseItem.setUpdatedAt(LocalDateTime.now());
        // Save WareHouse Item in table -> WarehouseItem
        wareHouseItem = wareHouseItemRepository.save(wareHouseItem);
        WareHouse wareHouse = getWareHouseById(wid);
        wareHouse.getWareHouseItems().add(wareHouseItem);
        saveWareHouse(wareHouse);
        return wareHouseItem;
    }

    public WareHouse assignManagerToWareHouse(UUID adminId,
                                              UUID wareHouseId,
                                              UUID wareHouseAdminId){
        // Verify all the ids
        AppUser admin = appUserService.getUserById(adminId);
        AppUser wareHouseAdmin = appUserService.getUserById(wareHouseAdminId);
        WareHouse wareHouse = getWareHouseById(wareHouseId);
        wareHouse.setManager(wareHouseAdmin);
        saveWareHouse(wareHouse);
        // Mail Service -> Notify WareHouse admin that a ware house is assigned to him
        return wareHouse;
    }
}