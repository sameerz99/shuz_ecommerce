package com.shuz.ecommerce.dto;

import com.shuz.ecommerce.entity.Category;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class ProductDto {
    private Integer id;
    private String name;
    private Integer categoryId;
    private Integer price;
    private String description;
    private String imageName;
}
