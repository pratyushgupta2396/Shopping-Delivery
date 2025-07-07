package com.instamart.shopping_delivery.service;

import com.instamart.shopping_delivery.models.AppUser;
import com.instamart.shopping_delivery.repositories.AppOrderRepo;
import com.instamart.shopping_delivery.repositories.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {


    AppUserRepo appUserRepo;
    @Autowired
    public AppUserService(AppUserRepo appUserRepo){
        this.appUserRepo = appUserRepo;
    }
    public void saveUser(AppUser user){
        appUserRepo.save(user);
        appUserRepo.findAll();
        appUserRepo.delete(user);

    }
}
