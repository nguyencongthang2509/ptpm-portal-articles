package com.articlesproject.core.user.repository;


import com.articlesproject.core.user.model.request.UserMyArticleByStatusRequest;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserMyArticleRepository extends ArticlesRepository {
    @Query(value = """
            SELECT ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status,ar.users_id, us.img, us.name, COUNT(tyms.article_id) AS 'tym' , IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite',GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
            FROM articles ar
            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
            LEFT JOIN tyms ON tyms.article_id = ar.id
            LEFT JOIN users us ON us.id = ar.users_id
            WHERE ar.users_id = :userId
            AND ( :#{#request.title} IS NULL
                OR :#{#request.title} LIKE ''
                OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION))
            AND (ar.status != 5)
            GROUP BY  ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status, ar.users_id, us.img, us.name
            """, countQuery = """
            SELECT COUNT(ar.id)
            FROM articles ar
            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
            LEFT JOIN tyms ON tyms.article_id = ar.id
            LEFT JOIN users us ON us.id = ar.users_id
            WHERE ar.users_id = :userId
            AND ( :#{#request.title} IS NULL
                OR :#{#request.title} LIKE ''
                OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION))
            AND (ar.status != 5)
            GROUP BY  ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status, ar.users_id, us.img, us.name
            """, nativeQuery = true)
    Page<UserMyArticleResponse> getAllMyArticle(Pageable page, @Param("userId") String userId, @Param("request") UserMyArticleRequest request);

    @Query(value = """
            SELECT ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status,ar.users_id, us.img, us.name, COUNT(tyms.article_id) AS 'tym' , IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite',GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
            FROM articles ar
            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
            LEFT JOIN tyms ON tyms.article_id = ar.id
            LEFT JOIN users us ON us.id = ar.users_id
            WHERE ar.users_id = :userId
            AND ( :#{#request.title} IS NULL
                OR :#{#request.title} LIKE ''
                OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION))
            AND (ar.status = :#{#request.status}) 
            GROUP BY  ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status, ar.users_id, us.img, us.name
            """, countQuery = """
                SELECT ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status,ar.users_id, us.img, us.name, COUNT(tyms.article_id) AS 'tym' , IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite',GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                FROM articles ar
                LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                LEFT JOIN tyms ON tyms.article_id = ar.id
                LEFT JOIN users us ON us.id = ar.users_id
                WHERE ar.users_id = :userId
                AND ( :#{#request.title} IS NULL
                    OR :#{#request.title} LIKE ''
                    OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION))
                AND (ar.status = :#{#request.status}) 
                GROUP BY  ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status, ar.users_id, us.img, us.name
            """, nativeQuery = true)
    Page<UserMyArticleResponse> getAllMyArticleByStatus(Pageable page, @Param("userId") String userId, @Param("request") UserMyArticleByStatusRequest request);

    @Query(value = """
             SELECT ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status,ar.users_id, us.img, us.name,  COUNT(tyms.article_id) AS 'tym', 
             IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
            WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  
            , GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
            FROM articles ar
            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
            LEFT JOIN tyms ON tyms.article_id = ar.id
            LEFT JOIN users us ON us.id = ar.users_id
            WHERE ar.id = :id
            AND (ar.status != 5)
            GROUP BY  ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status, ar.users_id, us.img, us.name
            """, nativeQuery = true)
    Optional<UserArticleResponse> findArticleById(@Param("id") String id, @Param("userId") String userId);

    @Query(value = """
             SELECT ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status,ar.users_id, us.img, us.name,  COUNT(tyms.article_id) AS 'tym', 
             IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
            WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  
            , GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
            FROM articles ar
            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
            LEFT JOIN tyms ON tyms.article_id = ar.id
            LEFT JOIN users us ON us.id = ar.users_id
            WHERE ar.id = :id
            AND (ar.status != 5)
            GROUP BY  ar.id, ar.title, ar.descriptive, ar.browse_date, ar.status, ar.users_id, us.img, us.name
            """, nativeQuery = true)
    Optional<UserArticleResponse> findArticleUpdateById(@Param("id") String id, @Param("userId") String userId);
}
