package com.shuz.ecommerce.service.impl;

import com.shuz.ecommerce.dto.Response.CartResponseDto;
import com.shuz.ecommerce.dto.request.CartRequestDto;
import com.shuz.ecommerce.entity.Cart;
import com.shuz.ecommerce.entity.Product;
import com.shuz.ecommerce.entity.User;
import com.shuz.ecommerce.repo.CartRepo;
import com.shuz.ecommerce.repo.ProductRepo;
import com.shuz.ecommerce.repo.UserRepo;
import com.shuz.ecommerce.service.CartService;
import com.shuz.ecommerce.service.other.MiscService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    MiscService miscService;
    @Override
    public CartResponseDto addProductToCart(CartRequestDto dto) {
        User loggedInUser = miscService.getLoggedInUser();
        //Finding the product
        Product selectedProduct = productRepo.findById(dto.getProduct_id()).get();
        //Creating the new cart object
        Cart newCart = new Cart();

        //Adding details to it
        newCart.setProduct(selectedProduct);
        newCart.setUser(loggedInUser);

        return new CartResponseDto(cartRepo.save(newCart));
    }

    @Override
    public void deleteItemInCart(Integer id) {
        cartRepo.deleteById(id);
    }

    @Override
    public List<CartResponseDto> getAllCartItemsOfUser() {
        User loggedInUser = miscService.getLoggedInUser();

        List<CartResponseDto> allCartItems = new ArrayList<>();

        //Getting the cart item for the logged in user
        List<Cart> foundCartItemsOfUser = cartRepo.findByUser(loggedInUser);

        //Converting the Cart to CartResponseDto
        for(Cart each: foundCartItemsOfUser){
            allCartItems.add(new CartResponseDto(each));
        }
        return allCartItems;
    }

    public Integer getTotalCartValueOfUser(){
        Integer total = 0;
        //Get all the items in the cart
        List<CartResponseDto> allCartItems = getAllCartItemsOfUser();

        //Calculating the total
        for(CartResponseDto each: allCartItems){
            total += (each.getProduct().getPrice());
        }
        return total;
    }

}
