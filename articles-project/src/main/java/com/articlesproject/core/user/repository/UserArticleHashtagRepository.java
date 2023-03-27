package com.articlesproject.core.user.repository;

import com.articlesproject.entity.ArticlesHashtag;
import com.articlesproject.repository.ArticlesHashtagRepository;

import java.util.List;

public interface UserArticleHashtagRepository extends ArticlesHashtagRepository {
    List<ArticlesHashtag> findByArticlesId(String articleId);

//
//    Optional<ArticlesHashtag> findByArticleId(String articleId);

//    void deleteByAticleId(String id);
}
