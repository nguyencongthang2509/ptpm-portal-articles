package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.request.CreateCommentRequest;
import com.articlesproject.core.user.model.request.UpdateCommentRequest;
import com.articlesproject.core.user.model.response.UserCommentResponse;
import com.articlesproject.core.user.repository.UserCommentRepository;
import com.articlesproject.core.user.service.UserCommentService;
import com.articlesproject.entity.Comments;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UserCommentServiceImpl implements UserCommentService {

    @Autowired
    private UserCommentRepository commentRepository;

    private FormUtils formUtils = new FormUtils();

    @Override
    public List<UserCommentResponse> findCommentByArticleId(String articleId) {
        return commentRepository.findCommentByArticleId(articleId);
    }

    @Override
    public Comments create(CreateCommentRequest request, String userId) {
        Comments comment = formUtils.convertToObject(Comments.class, request);
        comment.setUsersId(userId);
        return commentRepository.save(comment);
    }

    @Override
    public Comments update(UpdateCommentRequest request) {
        Optional<Comments> comment = commentRepository.findById(request.getId());
        if(!comment.isPresent()){
            throw new RestApiException(Message.COMMENT_NOT_EXIST);
        }
        comment.get().setContent(request.getContent());
        return commentRepository.save(comment.get());
    }

    @Override
    public boolean delete(String commentId) {
        Optional<Comments> comment = commentRepository.findById(commentId);
        if(!comment.isPresent()){
            throw new RestApiException(Message.COMMENT_NOT_EXIST);
        }
        commentRepository.deleteByReply(commentId);
        commentRepository.deleteById(commentId);
        return true;
    }
}
