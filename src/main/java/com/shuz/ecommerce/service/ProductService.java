package com.shuz.ecommerce.service;

import com.shuz.ecommerce.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();
    void addProduct(Product product);
    void deleteProductById(Integer id);
    Optional<Product> getProductById(Integer id);
    List<Product> getAllProductByCategoryId(Integer id);
}
