package com.shuz.ecommerce.service;

import com.shuz.ecommerce.dto.Response.CartResponseDto;
import com.shuz.ecommerce.dto.request.CartRequestDto;
import com.shuz.ecommerce.entity.Cart;

import java.util.List;

public interface CartService {
    CartResponseDto addProductToCart(CartRequestDto dto);
    void deleteItemInCart(Integer id);
    List<CartResponseDto> getAllCartItemsOfUser();
    Integer getTotalCartValueOfUser();

}
