package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.request.UserCreateArticleAlbumRequest;
import com.articlesproject.core.user.repository.UserArticleAlbumRepository;
import com.articlesproject.core.user.repository.UserArticleRepository;
import com.articlesproject.core.user.service.UserArticleAlbumService;
import com.articlesproject.entity.Articles;
import com.articlesproject.entity.ArticlesAlbum;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UserArticleAlbumServiceImpl implements UserArticleAlbumService {

    @Autowired
    private UserArticleAlbumRepository articleAlbumRepository;

    @Autowired
    private UserArticleRepository articleRepository;

    private FormUtils formUtils = new FormUtils();

    @Override
    public ArticlesAlbum createArticleAlbum(UserCreateArticleAlbumRequest request) {
        ArticlesAlbum articlesAlbum = new ArticlesAlbum();
        articlesAlbum.setArticlesId(request.getArticlesId());
        articlesAlbum.setAlbumId(request.getAlbumId());
        articleAlbumRepository.save(articlesAlbum);
        return articlesAlbum ;
    }

    @Override
    public boolean deleteArticleAlbum(String articleId, String albumId) {
        Optional<Articles> articles = articleRepository.findById(articleId);
        if(!articles.isPresent()){
            throw new RestApiException(Message.ARTICLE_NOT_EXIT);
        }

        articleAlbumRepository.deleteByArticlesIdAndAlbumId(articleId, albumId);
        return true;
    }
}

