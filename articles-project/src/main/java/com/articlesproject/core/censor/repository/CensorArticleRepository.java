package com.articlesproject.core.censor.repository;

import com.articlesproject.core.censor.model.request.ArticleRequest;
import com.articlesproject.core.censor.model.response.ArticleNotApproveResponse;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.entity.Articles;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CensorArticleRepository extends ArticlesRepository {

    List<Articles> findByCategoryId(String categoryId);

    @Query(value = """
             SELECT ar.id, ar.title, ar.browse_date, ar.users_id, us.img, us.name,
                GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                FROM articles ar
                LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                LEFT JOIN tyms ON tyms.article_id = ar.id
                LEFT JOIN users us ON us.id = ar.users_id
                WHERE ar.status = 2
                GROUP BY  ar.id, ar.title, ar.browse_date, ar.status, ar.users_id, us.name
                ORDER BY ar.created_date DESC
            """, countQuery = """
             SELECT count(ar.id)
                            FROM articles ar
                            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                            LEFT JOIN tyms ON tyms.article_id = ar.id
                            LEFT JOIN users us ON us.id = ar.users_id
                            WHERE ar.status = 2
                            GROUP BY  ar.id, ar.title, ar.browse_date, ar.status, ar.users_id, us.name
                            ORDER BY ar.created_date DESC
            """, nativeQuery = true)
    Page<ArticleNotApproveResponse> getAllArticleNotApprove(Pageable pageable,@Param("req")  ArticleRequest request);

    @Query(value = """       
             SELECT ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status,ar.users_id, us.img, us.name,
                GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                FROM articles ar
                LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                LEFT JOIN tyms ON tyms.article_id = ar.id
                LEFT JOIN users us ON us.id = ar.users_id
                WHERE ar.id = :id
                AND ar.status = 2
                GROUP BY  ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status, ar.users_id, us.name
            """, nativeQuery = true)
    Optional<ArticleNotApproveResponse> findArticleById(@Param("id") String id);
}
