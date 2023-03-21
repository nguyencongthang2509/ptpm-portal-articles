package com.articlesproject.core.user.repository;


import com.articlesproject.core.user.model.request.UserFindArticleRequest;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserArticleRepository extends ArticlesRepository {

    @Query(value = """
            
             SELECT ar.id, ar.title, ar.browse_date,  COUNT(tyms.article_id) AS 'tym', IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
                        WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  , GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                        FROM articles ar
                        LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                        LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                        LEFT JOIN tyms ON tyms.article_id = ar.id
                        WHERE ar.id = :id
                        GROUP BY  ar.id, ar.title, ar.browse_date
            """, nativeQuery = true)
    Optional<UserArticleResponse> findArticleById(@Param("id") String id, @Param("userId") String userId);

    @Query(value = """
             SELECT ar.id, ar.title, ar.browse_date,  COUNT(tyms.article_id) AS 'tym', IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
                        WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  , GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                        FROM articles ar
                        LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                        LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                        LEFT JOIN articles_album aral ON aral.articles_id = ar.id
                        LEFT JOIN tyms ON tyms.article_id = ar.id
            WHERE  ( :#{#request.title} IS NULL
                     OR :#{#request.title} LIKE ''
                     OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION) 
                     OR ar.title LIKE %:#{#request.title}% )
                     AND ( :#{#request.hashtag} IS NULL
                            OR :#{#request.hashtag} LIKE ''
                            OR ha.title LIKE :#{#request.hashtag} )
                     AND ( :#{#request.hashtag} IS NULL
                            OR :#{#request.hashtag} LIKE ''
                            OR ha.title LIKE :#{#request.hashtag} )
                     AND ( :#{#request.albumId} IS NULL
                                    OR :#{#request.albumId} LIKE ''
                                    OR aral.album_id LIKE :#{#request.albumId} )
            GROUP BY  ar.id, ar.title, ar.browse_date,  aral.articles_id
            """,
            countQuery = """
             SELECT ar.id, ar.title, ar.browse_date,  COUNT(tyms.article_id) AS 'tym', IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
                        WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  , GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                        FROM articles ar
                        LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                        LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                        LEFT JOIN articles_album aral ON aral.articles_id = ar.id
                        LEFT JOIN tyms ON tyms.article_id = ar.id
              WHERE ( :#{#request.title} IS NULL
                             OR :#{#request.title} LIKE ''
                             OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION) 
                             OR ar.title LIKE %:#{#request.title}% )
                             AND ( :#{#request.hashtag} IS NULL
                                    OR :#{#request.hashtag} LIKE ''
                                    OR ha.title LIKE :#{#request.hashtag} )
                             AND ( :#{#request.hashtag} IS NULL
                                    OR :#{#request.hashtag} LIKE ''
                                    OR ha.title LIKE :#{#request.hashtag} )
                             AND ( :#{#request.albumId} IS NULL
                                            OR :#{#request.albumId} LIKE ''
                                            OR aral.album_id LIKE :#{#request.albumId} )
                    GROUP BY  ar.id, ar.title, ar.browse_date,  aral.articles_id
                                    """
            , nativeQuery = true)
    Page<UserArticleResponse> findAllArticle(Pageable page, @Param("userId") String userId, @Param("request") UserFindArticleRequest request);
}
