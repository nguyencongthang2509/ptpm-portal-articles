package com.articlesproject.core.user.repository;


import com.articlesproject.core.user.model.request.UserFindArticleAuthorRequest;
import com.articlesproject.core.user.model.request.UserFindArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserArticleRepository extends ArticlesRepository {

    @Query(value = """       
             SELECT ar.id, ar.title, ar.browse_date, ar.status,ar.users_id, us.img, us.name, COUNT(tyms.article_id) AS 'tym', IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
                WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  , 
                GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                FROM articles ar
                LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                LEFT JOIN tyms ON tyms.article_id = ar.id
                LEFT JOIN users us ON us.id = ar.users_id
                WHERE ar.id = :id
                AND ar.status = 3
                GROUP BY  ar.id, ar.title, ar.browse_date, ar.status, ar.users_id, us.name
            """, nativeQuery = true)
    Optional<UserArticleResponse> findArticleById(@Param("id") String id, @Param("userId") String userId);

    @Query(value = """
             SELECT ar.id, ar.title, ar.browse_date, ar.status,ar.users_id, us.img, us.name,  COUNT(tyms.article_id) AS 'tym', IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
                WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  , GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                FROM articles ar
                LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                LEFT JOIN articles_album aral ON aral.articles_id = ar.id
                LEFT JOIN tyms ON tyms.article_id = ar.id
                LEFT JOIN users us ON us.id = ar.users_id
                LEFT JOIN category ca ON ca.id = ar.category_id
                WHERE  ar.status = 3
                AND ( :#{#request.title} IS NULL
                        OR :#{#request.title} LIKE ''
                        OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION))
                AND ( :#{#request.hashtag} IS NULL
                        OR :#{#request.hashtag} LIKE ''
                        OR ha.title LIKE :#{#request.hashtag} )
                AND ( :#{#request.category} IS NULL
                        OR :#{#request.category} LIKE ''
                        OR MATCH(ca.name) AGAINST( :#{#request.category} WITH QUERY EXPANSION) )
                AND ( :#{#request.albumId} IS NULL
                        OR :#{#request.albumId} LIKE ''
                        OR aral.album_id LIKE :#{#request.albumId} )
            GROUP BY  ar.id, ar.title, ar.browse_date, ar.status,  aral.articles_id, ar.users_id, us.name
            """,
            countQuery = """
                        SELECT ar.id, ar.title, ar.browse_date, ar.status,ar.users_id, us.img, us.name,  COUNT(tyms.article_id) AS 'tym', IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
                           WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  , GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                           FROM articles ar
                           LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                           LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                           LEFT JOIN articles_album aral ON aral.articles_id = ar.id
                           LEFT JOIN tyms ON tyms.article_id = ar.id
                           LEFT JOIN users us ON us.id = ar.users_id
                           LEFT JOIN category ca ON ca.id = ar.category_id
                           WHERE ( :#{#request.title} IS NULL
                                OR :#{#request.title} LIKE ''
                                OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION))
                                AND ( :#{#request.hashtag} IS NULL
                                       OR :#{#request.hashtag} LIKE ''
                                       OR ha.title LIKE :#{#request.hashtag} )
                                AND ( :#{#request.category} IS NULL
                                       OR :#{#request.category} LIKE ''
                                       OR MATCH(ca.name) AGAINST( :#{#request.category} WITH QUERY EXPANSION) )
                                AND ( :#{#request.albumId} IS NULL
                                       OR :#{#request.albumId} LIKE ''
                                       OR aral.album_id LIKE :#{#request.albumId} )
                                AND (ar.status = 3)
                               GROUP BY  ar.id, ar.title, ar.browse_date, ar.status,  aral.articles_id, ar.users_id, us.img, us.name
                                               """
            , nativeQuery = true)
    Page<UserArticleResponse> findAllArticle(Pageable page, @Param("userId") String userId, @Param("request") UserFindArticleRequest request);


    @Query(value = """
             SELECT ar.id, ar.title, ar.browse_date, ar.status,ar.users_id, us.img, us.name,  COUNT(tyms.article_id) AS 'tym', IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
                WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  , GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                FROM articles ar
                LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                LEFT JOIN articles_album aral ON aral.articles_id = ar.id
                LEFT JOIN tyms ON tyms.article_id = ar.id
                LEFT JOIN users us ON us.id = ar.users_id
                LEFT JOIN category ca ON ca.id = ar.category_id
                WHERE  ar.status = 3
                AND ( :#{#request.title} IS NULL
                OR :#{#request.title} LIKE ''
                OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION))
                AND ( :#{#request.hashtag} IS NULL
                        OR :#{#request.hashtag} LIKE ''
                        OR ha.title LIKE :#{#request.hashtag} )
                AND ( :#{#request.category} IS NULL
                        OR :#{#request.category} LIKE ''
                        OR MATCH(ca.name) AGAINST( :#{#request.category} WITH QUERY EXPANSION) )
                AND ( :#{#request.albumId} IS NULL
                        OR :#{#request.albumId} LIKE ''
                        OR aral.album_id LIKE :#{#request.albumId} )
            GROUP BY  ar.id, ar.title, ar.browse_date, ar.status,  aral.articles_id, ar.users_id, us.name
            ORDER BY ar.browse_date DESC
            """,
            countQuery = """
                    SELECT ar.id, ar.title, ar.browse_date, ar.status,ar.users_id, us.img, us.name,  COUNT(tyms.article_id) AS 'tym', IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
                       WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  , GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                       FROM articles ar
                       LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                       LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                       LEFT JOIN articles_album aral ON aral.articles_id = ar.id
                       LEFT JOIN tyms ON tyms.article_id = ar.id
                       LEFT JOIN users us ON us.id = ar.users_id
                       LEFT JOIN category ca ON ca.id = ar.category_id
                       WHERE ( :#{#request.title} IS NULL
                            OR :#{#request.title} LIKE ''
                            OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION))
                            AND ( :#{#request.hashtag} IS NULL
                                   OR :#{#request.hashtag} LIKE ''
                                   OR ha.title LIKE :#{#request.hashtag} )
                            AND ( :#{#request.category} IS NULL
                                   OR :#{#request.category} LIKE ''
                                   OR ca.name LIKE :#{#request.category} )
                            AND ( :#{#request.albumId} IS NULL
                                   OR :#{#request.albumId} LIKE ''
                                   OR MATCH(ca.name) AGAINST( :#{#request.category} WITH QUERY EXPANSION) )
                            AND (ar.status = 3)
                           GROUP BY  ar.id, ar.title, ar.browse_date, ar.status,  aral.articles_id, ar.users_id, us.img, us.name
                           ORDER BY ar.browse_date DESC
                           """
            , nativeQuery = true)
    Page<UserArticleResponse> findAllArticleByBrowseDate(Pageable page, @Param("userId") String userId, @Param("request") UserFindArticleRequest request);

    @Query(value = """       
             SELECT ar.id, ar.title, ar.browse_date, ar.status,ar.users_id, us.img, us.name, COUNT(tyms.article_id) AS 'tym', IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
                WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  , 
                GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                FROM articles ar
                LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                LEFT JOIN tyms ON tyms.article_id = ar.id
                LEFT JOIN users us ON us.id = ar.users_id
                WHERE ar.users_id = :#{#request.userId}
                AND ar.status = 3
                GROUP BY  ar.id, ar.title, ar.browse_date, ar.status, ar.users_id, us.img, us.name
            """, countQuery = """
                 SELECT ar.id, ar.title, ar.browse_date, ar.status,ar.users_id, us.img, us.name, COUNT(tyms.article_id) AS 'tym', IF((SELECT SUM(IF(ty.article_id IS NULL, 0, 1))  FROM tyms ty
                WHERE (:userId IS NULL OR ty.users_id = :userId) AND ty.article_id = ar.id) IS NULL,0,1) AS 'favorite'  , 
                GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                            FROM articles ar
                            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                            LEFT JOIN tyms ON tyms.article_id = ar.id
                            LEFT JOIN users us ON us.id = ar.users_id
                            WHERE ar.users_id = :#{#request.userId}
                            AND ar.status = 3
                            GROUP BY  ar.id, ar.title, ar.browse_date, ar.status, ar.users_id, us.img, us.name
            """, nativeQuery = true)
    Page<UserArticleResponse> findArticleByIdAuthorId(Pageable page, @Param("userId") String userId, @Param("request") UserFindArticleAuthorRequest request);
}
