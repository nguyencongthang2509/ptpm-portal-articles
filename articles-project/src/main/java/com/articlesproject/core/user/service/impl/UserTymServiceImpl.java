package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.request.UserCreateTymRequest;
import com.articlesproject.core.user.model.response.UserTymResponse;
import com.articlesproject.core.user.repository.UserArticleRepository;
import com.articlesproject.core.user.repository.UserTymRepository;
import com.articlesproject.core.user.service.UserTymService;
import com.articlesproject.entity.Articles;
import com.articlesproject.entity.Tyms;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
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
public class UserTymServiceImpl implements UserTymService {

    @Autowired
    private UserTymRepository tymRepository;

    @Autowired
    private UserArticleRepository articleRepository;

    @Override
    public List<UserTymResponse> getAllArticleFavorite(String userId) {
        return tymRepository.getAllArticleFavorite(userId);
    }

    @Override
    public Tyms favoriteArticle(String userId, UserCreateTymRequest request) {
        Optional<Articles> articles = articleRepository.findById(request.getArticlesId());
        if(!articles.isPresent()){
            throw new RestApiException(Message.ARTICLE_NOT_EXIT);
        }
        articles.get().setTym(articles.get().getTym() + 1);
        articleRepository.save(articles.get());
        Tyms tyms = new Tyms();
        tyms.setArticleId(request.getArticlesId());
        tyms.setUsersId(userId);
        return tymRepository.save(tyms);
    }

    @Override
    public boolean unfavoriteArticle(String id) {
        Optional<Tyms> tym = tymRepository.findById(id);
        if(!tym.isPresent()){
            throw new RestApiException(Message.ERROR_UNKNOWN);
        }
        Optional<Articles> articles = articleRepository.findById(tym.get().getArticleId());
        if(!articles.isPresent()){
            throw new RestApiException(Message.ARTICLE_NOT_EXIT);
        }
        articles.get().setTym(articles.get().getTym() - 1);
        articleRepository.save(articles.get());
        tymRepository.deleteById(id);
        return true;
    }
}
