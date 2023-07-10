package com.shuz.ecommerce.service.impl;

import com.shuz.ecommerce.entity.Product;
import com.shuz.ecommerce.repo.ProductRepo;
import com.shuz.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProduct(){
        return productRepo.findAll();
    }
    public void addProduct(Product product){
        productRepo.save(product);
    }
    public void deleteProductById(Integer id){
        productRepo.deleteById(id);
    }
    public Optional<Product> getProductById(Integer id){
        return productRepo.findById(id);
    }

    public List<Product> getAllProductByCategoryId(Integer id){
        return productRepo.findAllByCategory_Id(id);
    }

}
