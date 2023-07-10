package com.shuz.ecommerce.dto.request;

import com.shuz.ecommerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistRequestDto {
    private Integer product_id;
}
