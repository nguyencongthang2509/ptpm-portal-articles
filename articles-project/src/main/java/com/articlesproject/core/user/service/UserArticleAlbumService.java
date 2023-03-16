package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.request.UserCreateArticleAlbumRequest;
import com.articlesproject.entity.ArticlesAlbum;

public interface UserArticleAlbumService {

    ArticlesAlbum favoriteArticle(UserCreateArticleAlbumRequest request);

    boolean unfavoriteArticle(String id);

}
