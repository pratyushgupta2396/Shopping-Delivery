
package com.instamart.shopping_delivery.utility;

import com.instamart.shopping_delivery.dto.ProductDto;
import com.instamart.shopping_delivery.dto.WareHouseRegistrationDto;
import com.instamart.shopping_delivery.models.AppUser;
import com.instamart.shopping_delivery.models.Location;
import com.instamart.shopping_delivery.models.Product;
import com.instamart.shopping_delivery.models.WareHouse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MappingUtility {

    public WareHouse mapWareHouseToDtoToModel(WareHouseRegistrationDto wareHouseRegistrationDto,
                                              Location location){
        WareHouse wareHouse = new WareHouse();
        wareHouse.setName(wareHouseRegistrationDto.getWareHouseName());
        wareHouse.setLocation(location);
        wareHouse.setCreatedAt(LocalDateTime.now());
        wareHouse.setUpdatedAt(LocalDateTime.now());
        return wareHouse;
    }

    public Product mapProductDtoToProduct(ProductDto productDto,
                                          AppUser user){
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setOwnerCompany(productDto.getOwnerCompany());
        product.setTotalQuantity(productDto.getTotalQuantity());
        product.setUnitPrice(product.getUnitPrice());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setCreatedBy(user);
        return product;
    }
}
