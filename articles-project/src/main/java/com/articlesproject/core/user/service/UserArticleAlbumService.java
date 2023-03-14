package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.request.CreateArticleAlbumRequest;
import com.articlesproject.core.user.model.request.UpdateArticleAlbumRequest;
import com.articlesproject.entity.ArticlesAlbum;

public interface UserArticleAlbumService {

    ArticlesAlbum create(CreateArticleAlbumRequest request);

    ArticlesAlbum update(UpdateArticleAlbumRequest request);

    boolean delete(String id);
}
