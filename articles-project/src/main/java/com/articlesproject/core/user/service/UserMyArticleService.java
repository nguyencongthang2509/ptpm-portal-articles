package com.articlesproject.core.user.service;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.entity.Articles;

public interface UserMyArticleService {
    PageableObject<UserMyArticleResponse> getAllMyArticle(final UserMyArticleRequest request, String idUser);

    Articles updateArticle(String id, UserUpdateArticleRequest request);

    UserArticleResponse getArticleById(String id);
}
