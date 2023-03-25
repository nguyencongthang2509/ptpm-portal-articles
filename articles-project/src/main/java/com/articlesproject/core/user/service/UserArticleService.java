package com.articlesproject.core.user.service;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserFindArticleAuthorRequest;
import com.articlesproject.core.user.model.request.UserFindArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.entity.Articles;
import com.articlesproject.infrastructure.projection.SimpleEntityProj;

import java.util.List;

public interface UserArticleService {
    PageableObject<UserArticleResponse> findAllArticle(String userId, final UserFindArticleRequest request);
    PageableObject<UserArticleResponse> findAllArticleByBrowseDate(String userId, final UserFindArticleRequest request);
    PageableObject<UserArticleResponse> findArticleByIdAuthorId(String userId, UserFindArticleAuthorRequest request);

    Articles add(UserCreateArticleRequest request);
    UserArticleResponse getArticleById(String userId, String id);
}
