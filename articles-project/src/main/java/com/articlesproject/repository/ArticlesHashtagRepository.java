package com.articlesproject.repository;

import com.articlesproject.entity.ArticlesHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ArticlesHashtagRepository.NAME)
public interface ArticlesHashtagRepository extends JpaRepository<ArticlesHashtag, String>{
    public static final String NAME = "BaseArticlesHashtagRepository";

    long deleteByArticlesId(String articleId);

}
