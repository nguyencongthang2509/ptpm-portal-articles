package com.articlesproject.core.user.repository;

import com.articlesproject.entity.ArticlesHashtag;
import com.articlesproject.repository.Articles_HashtagRepository;

import java.util.List;

public interface UserArticleHashtagRepository extends Articles_HashtagRepository {
    List<ArticlesHashtag> findByArticlesId(String articleId);
}
