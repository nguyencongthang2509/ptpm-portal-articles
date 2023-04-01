package com.articlesproject.core.censor.service.impl;

import com.articlesproject.core.censor.model.request.ArticleRequest;
import com.articlesproject.core.censor.model.request.UpdateStatusArticleRequest;
import com.articlesproject.core.censor.model.response.ArticleNotApproveResponse;
import com.articlesproject.core.censor.repository.CensorArticleRepository;
import com.articlesproject.core.censor.repository.CensorUserRepository;
import com.articlesproject.core.censor.service.CensorArticleService;
import com.articlesproject.entity.Articles;
import com.articlesproject.entity.Users;
import com.articlesproject.infrastructure.constant.ArticleStatus;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class CensorArticleServiceImpl implements CensorArticleService {

    @Autowired
    private CensorArticleRepository articleRepository;

    @Autowired
    private CensorUserRepository userRepository;

    @Autowired
    private EmailSender emailSender;

    @Override
    public Page<ArticleNotApproveResponse> getAllArticleNotApprove(final ArticleRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return articleRepository.getAllArticleNotApprove(pageable, request);
    }

    @Override
    public ArticleNotApproveResponse findArticleById(String id) {
        Optional<ArticleNotApproveResponse> article = articleRepository.findArticleById(id);
        if(!article.isPresent()){
            throw new RestApiException(Message.ARTICLE_NOT_EXIST);
        }
        return article.get();
    }

    @Override
    public Articles approveArticle(UpdateStatusArticleRequest request) {
        Optional<Articles> article = articleRepository.findById(request.getId());
        Optional<Users> users = userRepository.findById(article.get().getUsersId());
        if(!article.isPresent()){
            throw new RestApiException(Message.ARTICLE_NOT_EXIST);
        }
        article.get().setStatus(ArticleStatus.DA_PHE_DUYET);
        article.get().setBrowseDate(new Date().getTime());
        emailSender.sendEmail(users.get().getEmail(), "[FCR] Thông báo Bài Viết chờ phê duyệt",
                "Bài Viết "+article.get().getTitle()+" đã dược phê duyệt", request.getFeedback());
        return articleRepository.save(article.get());
    }

    @Override
    public Articles refuseArticle(UpdateStatusArticleRequest request) {
        Optional<Articles> article = articleRepository.findById(request.getId());
        Optional<Users> users = userRepository.findById(article.get().getUsersId());
        if(!article.isPresent()){
            throw new RestApiException(Message.ARTICLE_NOT_EXIST);
        }
        emailSender.sendEmail(users.get().getEmail(), "[FCR] Thông báo Bài Viết chờ phê duyệt",
                "Bài Viết "+article.get().getTitle()+" cần chỉnh sửa lại", request.getFeedback());
        article.get().setBrowseDate(new Date().getTime());
        article.get().setStatus(ArticleStatus.DA_HUY);
        return articleRepository.save(article.get());
    }
}
