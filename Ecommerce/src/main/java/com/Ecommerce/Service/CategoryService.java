package com.Ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.Entity.Category;
import com.Ecommerce.Repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService 
{
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() 
    {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) 
    {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category createCategory(Category category) 
    {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updatedCategory) 
    {
        Category existingCategory = categoryRepository.findById(id).orElse(null);
        if (existingCategory != null) 
        {
            existingCategory.setName(updatedCategory.getName());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    public void deleteCategory(Long id) 
    {
        categoryRepository.deleteById(id);
    }
}