package com.articlesproject.core.censor.service.impl;

import com.articlesproject.core.censor.model.request.ArticleRequest;
import com.articlesproject.core.censor.model.request.UpdateStatusArticleRequest;
import com.articlesproject.core.censor.model.response.ArticleNotApproveResponse;
import com.articlesproject.core.censor.repository.CensorArticleRepository;
import com.articlesproject.core.censor.service.CensorArticleService;
import com.articlesproject.entity.Articles;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CensorArticleServiceImpl implements CensorArticleService {

    @Autowired
    private CensorArticleRepository articleRepository;

    @Override
    public Page<ArticleNotApproveResponse> getAllArticleNotApprove(final ArticleRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return articleRepository.getAllArticleNotApprove(pageable, request);
    }

    @Override
    public Articles approveArticle(UpdateStatusArticleRequest request) {
        Optional<Articles> article = articleRepository.findById(request.getId());
        if(!article.isPresent()){
            throw new RestApiException(Message.ARTICLE_NOT_EXIST);
        }
        article.get().setStatus(3);
        return articleRepository.save(article.get());
    }

    @Override
    public Articles refuseArticle(UpdateStatusArticleRequest request) {
        Optional<Articles> article = articleRepository.findById(request.getId());
        if(!article.isPresent()){
            throw new RestApiException(Message.ARTICLE_NOT_EXIST);
        }
        article.get().setStatus(2);
        return articleRepository.save(article.get());
    }
}
