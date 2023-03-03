package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.infrastructure.projection.SimpleEntityProj;

import java.util.List;

public interface UserArticleService {
    List<UserArticleResponse> getAllArticle();
}
