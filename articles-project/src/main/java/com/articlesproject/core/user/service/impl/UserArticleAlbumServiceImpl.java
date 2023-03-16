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
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserArticleAlbumServiceImpl implements UserArticleAlbumService {

    @Autowired
    private UserArticleAlbumRepository articleAlbumRepository;

    @Autowired
    private UserArticleRepository articleRepository;

    private FormUtils formUtils = new FormUtils();

    @Override
    public boolean favoriteArticle(UserCreateArticleAlbumRequest request) {
        Optional<Articles> articles = articleRepository.findById(request.getArticlesId());
        if(!articles.isPresent()){
            throw new RestApiException(Message.ARTICLE_NOT_EXIT);
        }
        articles.get().setTym(articles.get().getTym() + 1);
        articleRepository.save(articles.get());
        Arrays.stream(request.getAlbumId()).forEach(item ->{
            ArticlesAlbum articlesAlbum = new ArticlesAlbum();
            articlesAlbum.setArticlesId(request.getArticlesId());
            articlesAlbum.setAlbumId(item);
            articleAlbumRepository.save(articlesAlbum);
        });
        return true ;
    }

    @Override
    public boolean unfavoriteArticle(String id) {
        Optional<ArticlesAlbum> articlesAlbum = articleAlbumRepository.findById(id);
        Optional<Articles> articles = articleRepository.findById(articlesAlbum.get().getArticlesId());
        if(!articles.isPresent()){
            throw new RestApiException(Message.ARTICLE_NOT_EXIT);
        }
        if(!articlesAlbum.isPresent()){
            throw new RestApiException(Message.ARTICLE_ALBUM_NOT_EXIST);
        }
        articles.get().setTym(articles.get().getTym() - 1);
        articleRepository.save(articles.get());
        articleAlbumRepository.deleteById(id);
        return true;
    }
}
