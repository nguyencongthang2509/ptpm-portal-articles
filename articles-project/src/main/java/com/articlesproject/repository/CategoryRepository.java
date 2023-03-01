package com.articlesproject.repository;

import com.articlesproject.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(CategoryRepository.NAME)
public interface CategoryRepository extends JpaRepository<Category, String> {

    public static final String NAME = "BaseCategoryRepository";
}
