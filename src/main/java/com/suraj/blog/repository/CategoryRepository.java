package com.suraj.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.blog.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
