package com.articlesproject.core.user.repository;

import com.articlesproject.repository.Articles_AlbumRepository;

public interface UserArticleAlbumRepository extends Articles_AlbumRepository {

    long deleteByAlbumId(String id);

    long deleteByArticlesId(String articleId);

}
