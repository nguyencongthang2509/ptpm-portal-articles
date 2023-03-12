package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.request.CreateArticleAlbumRequest;
import com.articlesproject.core.user.model.request.UpdateArticleAlbumRequest;
import com.articlesproject.entity.Articles_Album;

public interface UserArticleAlbumService {

    Articles_Album favoriteArticle(CreateArticleAlbumRequest request);

    boolean unfavoriteArticle(String id);

}
