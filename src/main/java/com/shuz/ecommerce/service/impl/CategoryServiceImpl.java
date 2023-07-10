package com.shuz.ecommerce.service.impl;

import com.shuz.ecommerce.entity.Category;
import com.shuz.ecommerce.repo.CategoryRepo;
import com.shuz.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;
    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }
    public void addCategory(Category category){
        categoryRepo.save(category);
    }
    public void deleteCategoryById(Integer id){
        categoryRepo.deleteById(id);
    }

    public Optional<Category> getCategoryById(Integer id){
        return categoryRepo.findById(id);
    }
}
