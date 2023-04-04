package com.articlesproject.core.user.service;

import com.articlesproject.core.common.base.UserCommentObject;
import com.articlesproject.core.user.model.request.UserCreateCommentRequest;
import com.articlesproject.core.user.model.request.UserUpdateCommentRequest;
import com.articlesproject.core.user.model.response.UserCommentResponse;
import com.articlesproject.entity.Comments;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import java.util.List;

public interface UserCommentService {

    List<UserCommentResponse> findCommentByArticleId(String articleId);

    UserCommentObject create(UserCreateCommentRequest request, String userId, StompHeaderAccessor headerAccessor);

    Comments update(UserUpdateCommentRequest request);

    boolean delete(String commentId);

}
