package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.request.CreateCommentRequest;
import com.articlesproject.core.user.model.request.UpdateCommentRequest;
import com.articlesproject.core.user.model.response.CommentResponse;
import com.articlesproject.entity.Comments;

import java.util.List;

public interface UserCommentService {

    List<CommentResponse> findCommentByArticleId(String articleId);

    Comments create(CreateCommentRequest request);

    Comments update(UpdateCommentRequest request);

    boolean delete(String commentId);

}
