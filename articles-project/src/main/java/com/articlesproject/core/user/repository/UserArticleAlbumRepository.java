package com.articlesproject.core.user.repository;

import com.articlesproject.repository.ArticlesAlbumRepository;

public interface UserArticleAlbumRepository extends ArticlesAlbumRepository {

    long deleteByAlbumId(String id);

    long deleteByArticlesIdAndAlbumId(String articleId, String albumId);

}
