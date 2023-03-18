package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.core.user.repository.UserMyArticleRepository;
import com.articlesproject.core.user.repository.UserRepository;
import com.articlesproject.core.user.service.UserMyArticleService;
import com.articlesproject.entity.Articles;
import com.articlesproject.entity.Users;
import com.articlesproject.infrastructure.constant.ArticleStatus;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserMyArticleServiceImpl implements UserMyArticleService {

    @Autowired
    private UserMyArticleRepository userMyArticleRepository;

    @Autowired
    private UserRepository userRepository;

    private final FormUtils formUtils = new FormUtils();

    @Override
    public PageableObject<UserMyArticleResponse> getAllMyArticle(UserMyArticleRequest request, String idUser) {
        Optional<Users> users = userRepository.findById(idUser);
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserMyArticleResponse> res = userMyArticleRepository.getAllMyArticle(pageable, users.get().getId());
        return new PageableObject<>(res);
    }

    @Override
    public Articles updateArticle(String id, UserUpdateArticleRequest request) {
        Optional<Articles> articles = userMyArticleRepository.findById(id);
        if (articles.isPresent()) {
            articles.get().setTitle(request.getTitle());
            articles.get().setCategoryId(request.getCategoryId());
            articles.get().setStatus(ArticleStatus.MOI_TAO);
        }
        return userMyArticleRepository.save(articles.get());
    }

    @Override
    public UserArticleResponse getArticleById(String id) {
        Optional<UserArticleResponse> articles = userMyArticleRepository.findArticleById(id);
        if(!articles.isPresent()){
            throw new RestApiException(Message.ERROR_UNKNOWN);
        }
        return articles.get();
    }

    @Override
    public Articles addArticle(UserCreateArticleRequest request) {
        Articles ar = formUtils.convertToObject(Articles.class, request);
        ar.setTym(0);
        ar.setStatus(ArticleStatus.MOI_TAO);
        return userMyArticleRepository.save(ar);
    }
}
