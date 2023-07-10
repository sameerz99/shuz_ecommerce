package com.shuz.ecommerce.dto.Response;

import com.shuz.ecommerce.entity.Cart;
import com.shuz.ecommerce.entity.Product;
import com.shuz.ecommerce.entity.Wishlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistResponseDto {
    private Integer id;
    private Product product;

    public WishlistResponseDto(Wishlist wishlist){
        this.id=wishlist.getId();
        this.product=wishlist.getProduct();
    }
}
