package com.articlesproject.repository;

import com.articlesproject.entity.Articles_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(Articles_CategoryRepository.NAME)
public interface Articles_CategoryRepository extends JpaRepository<Articles_Category, String>{
    public static final String NAME = "BaseArticles_CategoryRepository";
}
