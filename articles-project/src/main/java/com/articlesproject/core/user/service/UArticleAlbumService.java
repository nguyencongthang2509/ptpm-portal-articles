package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.request.CreateArticleAlbumRequest;
import com.articlesproject.core.user.model.request.UpdateArticleAlbumRequest;
import com.articlesproject.entity.Articles_Album;

public interface UArticleAlbumService {

    Articles_Album create(CreateArticleAlbumRequest request);

    Articles_Album update(UpdateArticleAlbumRequest request);

    boolean delete(String id);
}
