package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.request.CreateArticleAlbumRequest;
import com.articlesproject.entity.ArticlesAlbum;

public interface UserArticleAlbumService {

    ArticlesAlbum favoriteArticle(CreateArticleAlbumRequest request);

    boolean unfavoriteArticle(String id);

}
