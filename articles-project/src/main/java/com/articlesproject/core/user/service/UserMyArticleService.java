package com.articlesproject.core.user.service;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;

public interface UserMyArticleService {
    PageableObject<UserMyArticleResponse> getAllMyArticle(final UserMyArticleRequest request, String idUser);
}
