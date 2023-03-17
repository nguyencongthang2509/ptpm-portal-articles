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
            SELECT ar.id, ar.title, ar.browse_date, ar.tym, GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' FROM articles ar
            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
            GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym
            """,
            countQuery = """
                    SELECT ar.id, ar.title, ar.browse_date, ar.tym, GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' FROM articles ar
                    LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                    LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                    GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym
                            """
            , nativeQuery = true)
    Page<UserArticleResponse> getAllArticle(Pageable page, @Param("req") UserArticleRequest req);

    @Query(value = """
            SELECT a.id, a.title, a.browse_date, a.tym , GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' FROM articles a 
            WHERE a.id = :id
            GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym
            """, nativeQuery = true)
    Optional<UserArticleResponse> findArticleById(@Param("id") String id);

    @Query(value = """
            SELECT ar.id, ar.title, ar.browse_date, ar.tym, GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' FROM articles ar
            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
            WHERE  ( :#{#request.title} IS NULL
                     OR :#{#request.title} LIKE ''
                     OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION) 
                     OR ar.title LIKE %:#{#request.title}% )
                     AND ( :#{#request.hashtag} IS NULL
                            OR :#{#request.hashtag} LIKE ''
                            OR ha.title LIKE :#{#request.hashtag} )
            GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym
            """,
            countQuery = """
                            SELECT COUNT(ar.id) FROM articles ar
                    LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                    LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                    WHERE  ( :#{#request.title} IS NULL
                             OR :#{#request.title} LIKE ''
                             OR MATCH(ar.title) AGAINST( :#{#request.title} WITH QUERY EXPANSION) 
                             OR ar.title LIKE %:#{#request.title}% )
                             AND ( :#{#request.hashtag} IS NULL
                                    OR :#{#request.hashtag} LIKE ''
                                    OR ha.title LIKE :#{#request.hashtag} )
                    GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym
                            """
            , nativeQuery = true)
    Page<UserArticleResponse> FindAllArticle(Pageable page, @Param("request") UserFindArticleRequest request);
}
