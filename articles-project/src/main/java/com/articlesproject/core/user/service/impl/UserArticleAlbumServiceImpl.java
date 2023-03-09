package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.request.CreateArticleAlbumRequest;
import com.articlesproject.core.user.model.request.UpdateArticleAlbumRequest;
import com.articlesproject.core.user.repository.UserArticleAlbumRepository;
import com.articlesproject.core.user.service.UserArticleAlbumService;
import com.articlesproject.entity.Articles_Album;
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
    public Articles_Album create(CreateArticleAlbumRequest request) {
        Articles_Album articlesAlbum = formUtils.convertToObject(Articles_Album.class, request);
        return articleAlbumRepository.save(articlesAlbum);
    }

    @Override
    public Articles_Album update(UpdateArticleAlbumRequest request) {
        Optional<Articles_Album> articlesAlbum = articleAlbumRepository.findById(request.getId());
        if(!articlesAlbum.isPresent()){
            throw new RestApiException(Message.ARTICLE_ALBUM_NOT_EXIST);
        }
        articlesAlbum.get().setArticlesId(request.getArticlesId());
        return articleAlbumRepository.save(articlesAlbum.get());
    }

    @Override
    public boolean delete(String id) {
        Optional<Articles_Album> articlesAlbum = articleAlbumRepository.findById(id);
        if(!articlesAlbum.isPresent()){
            throw new RestApiException(Message.ARTICLE_ALBUM_NOT_EXIST);
        }
        articleAlbumRepository.deleteById(id);
        return true;
    }
}
