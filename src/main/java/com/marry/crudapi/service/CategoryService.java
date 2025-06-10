package com.marry.crudapi.service;

import com.marry.crudapi.entity.Category;
import com.marry.crudapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    //Get all categories
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
    //Get category by id
    public Category getById(Integer id){
        return categoryRepository.findById(id).orElse(null);
    }
    //Create category
    public Category create(Category category){
        return categoryRepository.save(category);
    }
    //Update category
    public Category update(Category category){
        return categoryRepository.save(category);
    }
    //Delete category
    public void delete(Integer id){
        categoryRepository.deleteById(id);
    }
}
