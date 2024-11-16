package com.ty.project.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.project.main.entities.Category;



public interface CategoryRepository extends JpaRepository<Category, Long> {

}
