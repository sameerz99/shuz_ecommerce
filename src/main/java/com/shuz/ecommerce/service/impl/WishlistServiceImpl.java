package com.shuz.ecommerce.service.impl;

import com.shuz.ecommerce.dto.Response.CartResponseDto;
import com.shuz.ecommerce.dto.Response.WishlistResponseDto;
import com.shuz.ecommerce.dto.request.WishlistRequestDto;
import com.shuz.ecommerce.entity.Cart;
import com.shuz.ecommerce.entity.Product;
import com.shuz.ecommerce.entity.User;
import com.shuz.ecommerce.entity.Wishlist;
import com.shuz.ecommerce.repo.CartRepo;
import com.shuz.ecommerce.repo.ProductRepo;
import com.shuz.ecommerce.repo.UserRepo;
import com.shuz.ecommerce.repo.WishlistRepo;
import com.shuz.ecommerce.service.WishlistService;
import com.shuz.ecommerce.service.other.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    WishlistRepo wishlistRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    MiscService miscService;
    @Override
    public WishlistResponseDto addProductToWishlist(WishlistRequestDto dto) {
        User loggedInUser = miscService.getLoggedInUser();
        //Finding the product
        Product selectedProduct = productRepo.findById(dto.getProduct_id()).get();
        //Creating the new cart object
        Wishlist newCart = new Wishlist();

        //Adding details to it
        newCart.setProduct(selectedProduct);
        newCart.setUser(loggedInUser);

        return new WishlistResponseDto(wishlistRepo.save(newCart));
    }

    @Override
    public void deleteItemInWishlist(Integer id) {
        wishlistRepo.deleteById(id);
    }

    @Override
    public List<WishlistResponseDto> getAllWishlistItemsOfUser() {
        User loggedInUser = miscService.getLoggedInUser();

        List<WishlistResponseDto> allWishlistItems = new ArrayList<>();


        List<Wishlist> foundWishlistItemsOfUser = wishlistRepo.findByUser(loggedInUser);


        for(Wishlist each: foundWishlistItemsOfUser){
            allWishlistItems.add(new WishlistResponseDto(each));
        }
        return allWishlistItems;
    }
    }

