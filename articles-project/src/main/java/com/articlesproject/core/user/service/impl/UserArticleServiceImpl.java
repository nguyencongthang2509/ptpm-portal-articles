package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserFindArticleAuthorRequest;
import com.articlesproject.core.user.model.request.UserFindArticleByCategoryRequest;
import com.articlesproject.core.user.model.request.UserFindArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.repository.UserArticleRepository;
import com.articlesproject.core.user.service.UserArticleService;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UserArticleServiceImpl implements UserArticleService {

    @Autowired
    private UserArticleRepository userArticleRepository;

    @Cacheable(value = "allArticle", key = "#userId + '_' + #request.page")
    @Override
    public PageableObject<UserArticleResponse> findAllArticle(String userId, UserFindArticleRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserArticleResponse> res = userArticleRepository.findAllArticle(pageable, userId, request);
        return new PageableObject<>(res);
    }

    @Override
    public PageableObject<UserArticleResponse> findAllArticleByCategory(String userId, UserFindArticleByCategoryRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserArticleResponse> res = userArticleRepository.findAllArticleByCategory(pageable, userId, request);
        return new PageableObject<>(res);
    }

    @Cacheable(value = "allArticleByBrowsedate", key = "#userId")
    @Override
    public PageableObject<UserArticleResponse> findAllArticleByBrowseDate(String userId, UserFindArticleRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserArticleResponse> res = userArticleRepository.findAllArticleByBrowseDate(pageable, userId, request);
        return new PageableObject<>(res);
    }

    @Cacheable(value = "allArticleByIdAuthor", key = "#userId")
    @Override
    public PageableObject<UserArticleResponse> findArticleByIdAuthorId(String userId, UserFindArticleAuthorRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserArticleResponse> res = userArticleRepository.findArticleByIdAuthorId(pageable, userId, request);
        return new PageableObject<>(res);
    }

    @Cacheable(value = "articleById", key = "#id")
    @Override
    public UserArticleResponse getArticleById(String userId, String id) {
        Optional<UserArticleResponse> articles = userArticleRepository.findArticleById(id, userId);
        if (!articles.isPresent()) {
            throw new RestApiException(Message.ERROR_UNKNOWN);
        }
        return articles.get();
    }

    @Cacheable(value = "allArticleByTym", key = "#userId")
    @Override
    public PageableObject<UserArticleResponse> findAllArticleByTym(String userId, UserFindArticleRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserArticleResponse> res = userArticleRepository.findAllArticleByTym(pageable, userId, request);
        return new PageableObject<>(res);
    }

}
