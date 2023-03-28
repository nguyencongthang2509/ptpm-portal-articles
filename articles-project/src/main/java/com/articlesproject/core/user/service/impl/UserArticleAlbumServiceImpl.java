package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserCreateArticleAlbumRequest;
import com.articlesproject.core.user.model.request.UserFindArticleByAlbumRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    public PageableObject<UserArticleResponse> findAllArticleByAlbum(String userId, UserFindArticleByAlbumRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserArticleResponse> res = articleAlbumRepository.findAllArticleByAlbum(pageable,userId, request);
        return new PageableObject<>(res);
    }
}

