package com.shuz.ecommerce.repo;

import com.shuz.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    List<Product> findAllByCategory_Id(Integer id);
}
