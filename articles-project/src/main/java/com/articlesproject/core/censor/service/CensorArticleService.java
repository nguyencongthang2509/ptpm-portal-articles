package com.articlesproject.core.censor.service;

import com.articlesproject.core.censor.model.request.ArticleRequest;
import com.articlesproject.core.censor.model.request.UpdateStatusArticleRequest;
import com.articlesproject.core.censor.model.response.ArticleNotApproveResponse;
import com.articlesproject.entity.Articles;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

public interface CensorArticleService {
    Page<ArticleNotApproveResponse> getAllArticleNotApprove(final ArticleRequest request);

    ArticleNotApproveResponse findArticleById(String id);

    Articles approveArticle(UpdateStatusArticleRequest request);

    Articles refuseArticle(UpdateStatusArticleRequest request);
}
