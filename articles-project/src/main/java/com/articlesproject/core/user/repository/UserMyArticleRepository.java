package com.articlesproject.core.user.repository;


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
            SELECT ar.id, ar.title, ar.browse_date, ar.tym , GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                                    FROM articles ar
                                    LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                                    LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                                    WHERE ar.users_id = :idUser
                                    GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym
            """, nativeQuery = true)
    Page<UserMyArticleResponse> getAllMyArticle(Pageable page, @Param("idUser") String idUser);

    @Query(value = """
            SELECT ar.id, ar.title, ar.browse_date, ar.tym,  GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' 
                        FROM articles ar
                        LEFT JOIN articles_hashtag  arha ON ar.id = arha.articles_id
                        LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                        WHERE ar.id = :id
                        GROUP BY  ar.id, ar.title, ar.browse_date, ar.tym
            """, nativeQuery = true)
    Optional<UserArticleResponse> findArticleById(@Param("id") String id);
}
