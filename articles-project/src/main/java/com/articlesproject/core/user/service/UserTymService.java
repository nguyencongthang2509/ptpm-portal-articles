package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.request.UserCreateTymRequest;
import com.articlesproject.core.user.model.response.UserTymResponse;
import com.articlesproject.entity.Tyms;

import java.util.List;

public interface UserTymService {

    List<UserTymResponse> getAllArticleFavorite(String userId);

    Tyms favoriteArticle(String userId, UserCreateTymRequest request);

    boolean unfavoriteArticle(String userId, String articleId);

}
