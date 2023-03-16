package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.request.UserCreateCommentRequest;
import com.articlesproject.core.user.model.request.UserUpdateCommentRequest;
import com.articlesproject.core.user.model.response.UserCommentResponse;
import com.articlesproject.entity.Comments;

import java.util.List;

public interface UserCommentService {

    List<UserCommentResponse> findCommentByArticleId(String articleId);

    Comments create(UserCreateCommentRequest request, String userId);

    Comments update(UserUpdateCommentRequest request);

    boolean delete(String commentId);

}
