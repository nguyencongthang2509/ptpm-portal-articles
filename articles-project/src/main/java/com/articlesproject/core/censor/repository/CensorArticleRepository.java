package com.articlesproject.core.censor.repository;

import com.articlesproject.core.censor.model.request.ArticleRequest;
import com.articlesproject.core.censor.model.response.ArticleNotApproveResponse;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.entity.Articles;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CensorArticleRepository extends ArticlesRepository {

    List<Articles> findByCategoryId(String categoryId);

    @Query(value = """
            SELECT ar.id, ar.title, ar.content, ar.created_date, us.name AS userName, us.code AS userCode, ca.name AS categoryName, ca.code AS categoryCode  FROM articles ar
            JOIN users us ON us.id = ar.users_id
            JOIN category ca ON ca.id = ar.category_id
            WHERE ar.status = 1
            ORDER BY ar.created_date DESC
            """, nativeQuery = true)
    Page<ArticleNotApproveResponse> getAllArticleNotApprove(Pageable pageable,@Param("req")  ArticleRequest request);
}
