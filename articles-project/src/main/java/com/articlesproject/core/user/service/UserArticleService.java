package com.articlesproject.core.user.service;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.infrastructure.projection.SimpleEntityProj;

import java.util.List;

public interface UserArticleService {
    PageableObject<UserArticleResponse> getAllArticle(final UserArticleRequest request);
}
