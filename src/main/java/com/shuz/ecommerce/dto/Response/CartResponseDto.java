package com.shuz.ecommerce.dto.Response;

import com.shuz.ecommerce.entity.Cart;
import com.shuz.ecommerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponseDto {
    private Integer id;
    private Product product;

    public CartResponseDto(Cart cart){
        this.id=cart.getId();
        this.product=cart.getProduct();
    }
}
