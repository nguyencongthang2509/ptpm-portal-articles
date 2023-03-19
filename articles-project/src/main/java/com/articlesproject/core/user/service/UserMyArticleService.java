package com.articlesproject.core.user.service;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.entity.Articles;

import java.io.IOException;

public interface UserMyArticleService {
    PageableObject<UserMyArticleResponse> getAllMyArticle(final UserMyArticleRequest request, String idUser);

    Articles updateArticle(String id, UserUpdateArticleRequest request) throws IOException;

    UserArticleResponse getArticleById(String id);

    Articles addArticle(UserCreateArticleRequest request);

    boolean deleteArticle(String id);
}
