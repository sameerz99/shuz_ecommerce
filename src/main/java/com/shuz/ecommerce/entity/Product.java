package com.shuz.ecommerce.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    private Integer price;
    private String description;
    private String imageName;

}
