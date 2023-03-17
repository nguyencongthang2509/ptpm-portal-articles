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
            SELECT ar.id, ar.title, ar.browse_date, ar.tym, IF(aral.articles_id IS NULL, 0, 1) AS 'favorite', GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' FROM articles ar
            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
            LEFT JOIN articles_album aral ON aral.articles_id = ar.id
            LEFT JOIN album ab ON ab.id = aral.album_id
            WHERE ( ab.users_id = :#{#request.userId}
            OR  ab.users_id IS NULL)
            GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym, aral.articles_id
            """,
            countQuery = """
                    SELECT COUNT(ar.id) FROM articles ar
                    LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                    LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                    LEFT JOIN articles_album aral ON aral.articles_id = ar.id
                    LEFT JOIN album ab ON ab.id = aral.album_id
                    WHERE (ab.users_id = :#{#request.userId}
                    OR  ab.users_id IS NULL)
                    GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym, aral.articles_id
                            """
            , nativeQuery = true)
    Page<UserArticleResponse> getAllArticle(Pageable page,  @Param("request") UserArticleRequest req);

    @Query(value = """
            SELECT ar.id, ar.title, ar.browse_date, ar.tym, IF(aral.articles_id IS NULL, 0, 1) AS 'favorite', GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' FROM articles ar
            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
            LEFT JOIN articles_album aral ON aral.articles_id = ar.id
            LEFT JOIN album ab ON ab.id = aral.album_id
            WHERE (ab.users_id = :userId
            OR  ab.users_id IS NULL) AND ar.id = :id
            GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym, aral.articles_id
            """, nativeQuery = true)
    Optional<UserArticleResponse> findArticleById(@Param("id") String id, @Param("userId") String userId);

    @Query(value = """
            SELECT ar.id, ar.title, ar.browse_date, ar.tym, IF(aral.articles_id IS NULL, 0, 1) AS 'favorite', GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' FROM articles ar
            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
            LEFT JOIN articles_album aral ON aral.articles_id = ar.id
            LEFT JOIN album ab ON ab.id = aral.album_id
            WHERE  (ab.users_id = :userId
                    OR  ab.users_id IS NULL)
            AND( :#{#request.title} IS NULL
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
            GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym, aral.articles_id
            """,
            countQuery = """
                            SELECT COUNT(ar.id) FROM articles ar
                            LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                            LEFT JOIN articles_album aral ON aral.articles_id = ar.id
                            LEFT JOIN album ab ON ab.id = aral.album_id
                            WHERE  (ab.users_id = :userId
                                    OR  ab.users_id IS NULL)
                                    AND ( :#{#request.title} IS NULL
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
                    GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym, aral.articles_id
                                    """
            , nativeQuery = true)
    Page<UserArticleResponse> FindAllArticle(Pageable page, @Param("userId") String userId, @Param("request") UserFindArticleRequest request);
}
