package com.articlesproject.repository;

import com.articlesproject.entity.ArticlesHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(Articles_HashtagRepository.NAME)
public interface Articles_HashtagRepository extends JpaRepository<ArticlesHashtag, String>{
    public static final String NAME = "BaseArticles_CategoryRepository";
}
