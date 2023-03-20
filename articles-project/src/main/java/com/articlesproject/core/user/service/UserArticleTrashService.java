package com.articlesproject.core.user.service;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserArticleTrashRequest;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.model.response.UserArticleTrashResponse;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.entity.Articles;

import java.io.IOException;

public interface UserArticleTrashService {
    PageableObject<UserArticleTrashResponse> getAllArticleTrash(final UserArticleTrashRequest request, String idUser);

    boolean deleteArticle(String id);
}
