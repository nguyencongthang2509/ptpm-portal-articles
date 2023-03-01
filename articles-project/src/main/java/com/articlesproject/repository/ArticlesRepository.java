package com.articlesproject.repository;

import com.articlesproject.entity.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ArticlesRepository.NAME)
public interface ArticlesRepository extends JpaRepository<Articles, String> {

    public static final String NAME = "BaseArticlesRepository";
}
