package com.shuz.ecommerce.service;

import com.shuz.ecommerce.dto.Response.CartResponseDto;
import com.shuz.ecommerce.dto.Response.WishlistResponseDto;
import com.shuz.ecommerce.dto.request.CartRequestDto;
import com.shuz.ecommerce.dto.request.WishlistRequestDto;

import java.util.List;

public interface WishlistService {
    WishlistResponseDto addProductToWishlist(WishlistRequestDto dto);
    void deleteItemInWishlist(Integer id);
    List<WishlistResponseDto> getAllWishlistItemsOfUser();
}
