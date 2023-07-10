package com.shuz.ecommerce.service;

import com.shuz.ecommerce.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategory();
    void addCategory(Category category);
    void deleteCategoryById(Integer id);
    Optional<Category> getCategoryById(Integer id);
}
