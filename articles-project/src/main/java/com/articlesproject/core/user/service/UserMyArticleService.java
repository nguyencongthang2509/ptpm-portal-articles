package com.articlesproject.core.user.service;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserMyArticleByStatusRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.entity.Articles;

import java.io.IOException;

public interface UserMyArticleService {
    PageableObject<UserMyArticleResponse> getAllMyArticle(final UserMyArticleByStatusRequest request, String userId);

    PageableObject<UserMyArticleResponse> getAllMyArticleByStatus(final UserMyArticleByStatusRequest request, String userId);

    Articles updateArticle(String id, UserUpdateArticleRequest request) throws IOException;

    UserArticleResponse getArticleById(String id, String userId);

    Articles addArticle(UserCreateArticleRequest request, String userId) throws IOException;

    boolean deleteArticle(String id);
}
