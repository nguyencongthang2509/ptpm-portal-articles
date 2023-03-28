package com.articlesproject.core.user.service;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserCreateArticleAlbumRequest;
import com.articlesproject.core.user.model.request.UserFindArticleByAlbumRequest;
import com.articlesproject.core.user.model.request.UserFindArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.entity.ArticlesAlbum;

public interface UserArticleAlbumService {

    ArticlesAlbum createArticleAlbum(UserCreateArticleAlbumRequest request);

    boolean deleteArticleAlbum(String articleId, String albumId);

    PageableObject<UserArticleResponse> findAllArticleByAlbum(String userId, final UserFindArticleByAlbumRequest request);

}
