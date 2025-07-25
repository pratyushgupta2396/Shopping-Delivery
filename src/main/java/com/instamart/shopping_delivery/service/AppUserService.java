package com.instamart.shopping_delivery.service;

import com.instamart.shopping_delivery.exceptions.InvalidOperationException;
import com.instamart.shopping_delivery.exceptions.UserNotExistException;
import com.instamart.shopping_delivery.models.AppUser;
import com.instamart.shopping_delivery.models.WareHouse;
import com.instamart.shopping_delivery.repositories.AppUserRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.UUID;

@Service
public class AppUserService {

    AppUserRepository appUserRepository;
    MailService mailService;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository,
                          MailService mailService){
        this.appUserRepository = appUserRepository;
        this.mailService = mailService;
    }

    public AppUser registerCustomer(AppUser customer){
        return appUserRepository.save(customer);
    }

    public AppUser wareHouseAdminInvite(UUID userId, AppUser wareHouseAdmin){
        // 1. First verify user who is inviting is of type appadmin or not.
        AppUser admin = appUserRepository.findById(userId).orElse(null);
        if(admin == null){
            // Throw exception that user does not exist
            throw new UserNotExistException(String.format("User with id %s does not exist", userId.toString()));
        }
        if(!admin.getUserType().equals("APP_ADMIN")){
            throw new InvalidOperationException("User is not allowed to invite warehosue admim");
        }
        wareHouseAdmin.setStatus("INACTIVE");
        wareHouseAdmin = appUserRepository.save(wareHouseAdmin);
        mailService.sendWareHouseInvitationMail(wareHouseAdmin);
        return wareHouseAdmin;
    }

    public void acceptWareHouseAdminInvite(UUID wareHouseAdminId){
        AppUser user =  appUserRepository.findById(wareHouseAdminId).orElse(null);
        user.setStatus("ACTIVE");
        appUserRepository.save(user);
    }

    public AppUser isAppAdmin(UUID userId){
        AppUser user = appUserRepository.findById(userId).orElse(null);
        if(user.getUserType().equals("APP_ADMIN")){
            return user;
        }
        return null;
    }

    public AppUser getUserById(UUID userId){
        return appUserRepository.findById(userId).orElse(null);
    }

}