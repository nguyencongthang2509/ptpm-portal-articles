package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.repository.UserArticleRepository;
import com.articlesproject.core.user.service.UserArticleService;
import com.articlesproject.entity.Articles;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.FormUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UserArticleServiceImpl implements UserArticleService {

    @Autowired
    private UserArticleRepository userArticleRepository;

    private final FormUtils formUtils = new FormUtils();

    @Override
    public PageableObject<UserArticleResponse> getAllArticle(final UserArticleRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserArticleResponse> res = userArticleRepository.getAllArticle(pageable, request);
        return new PageableObject<>(res);
    }

    @Override
    public Articles addArticle(UserCreateArticleRequest request) {
        Articles ar = formUtils.convertToObject(Articles.class, request);
        ar.setTym(0);
        ar.setStatus(1);
        return userArticleRepository.save(ar);
    }

    @Override
    public Articles updateArticle(String id, UserUpdateArticleRequest request) {
        Optional<Articles> articles = userArticleRepository.findById(id);
        if(articles.isPresent()){
            articles.get().setTitle(request.getTitle());
            articles.get().setCategoryId(request.getCategoryId());
            articles.get().setTym(0);
            articles.get().setStatus(1);
        }
        return userArticleRepository.save(articles.get());
    }

    @Override
    public boolean deleteArticle(String id) {
        Optional<Articles> articles = userArticleRepository.findById(id);
        if(!articles.isPresent()){
            throw new RestApiException(Message.ARTICLE_NOT_EXIT);
        }
        userArticleRepository.deleteById(id);
        return true;
    }

    @Override
    public Articles add(UserCreateArticleRequest request) {
        Articles ar = formUtils.convertToObject(Articles.class, request);
        ar.setTym(0);
        ar.setStatus(1);
        return userArticleRepository.save(ar);
    }

}
