package com.ty.project.main.services;

import org.springframework.data.domain.Page;

import com.ty.project.main.entities.Category;

public interface CategoryService {
	Page<Category> getAllCategories(int page, int size);
    Category createCategory(Category category);
    Category getCategoryById(Long id);
    Category updateCategory(Long id, Category categoryDetails);
    void deleteCategory(Long id);
}
