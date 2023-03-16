package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.request.UserCreateArticleAlbumRequest;
import com.articlesproject.core.user.repository.UserArticleAlbumRepository;
import com.articlesproject.core.user.service.UserArticleAlbumService;
import com.articlesproject.entity.ArticlesAlbum;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserArticleAlbumServiceImpl implements UserArticleAlbumService {

    @Autowired
    private UserArticleAlbumRepository articleAlbumRepository;

    private FormUtils formUtils = new FormUtils();

    @Override
    public ArticlesAlbum favoriteArticle(UserCreateArticleAlbumRequest request) {
        ArticlesAlbum articlesAlbum = formUtils.convertToObject(ArticlesAlbum.class, request);
        return articleAlbumRepository.save(articlesAlbum);
    }

    @Override
    public boolean unfavoriteArticle(String id) {
        Optional<ArticlesAlbum> articlesAlbum = articleAlbumRepository.findById(id);
        if(!articlesAlbum.isPresent()){
            throw new RestApiException(Message.ARTICLE_ALBUM_NOT_EXIST);
        }
        articleAlbumRepository.deleteById(id);
        return true;
    }
}
