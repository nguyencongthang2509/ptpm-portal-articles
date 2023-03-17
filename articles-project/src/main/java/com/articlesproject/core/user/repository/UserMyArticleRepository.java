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
    @Query(value = "SELECT a.id, a.title, a.browse_date, a.tym, IF(aral.articles_id IS NULL, 0, 1) AS 'favorite', \n" +
            "GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' FROM articles a\n" +
            "            LEFT JOIN articles_hashtag  arha ON a.id = arha.articles_id\n" +
            "            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id" +
            "            LEFT JOIN articles_album aral ON aral.articles_id = a.id\n" +
            "            LEFT JOIN album ab ON ab.id = aral.album_id where a.users_id = :idUser" +
            " GROUP BY  a.id, a.title, a.browse_date, a.tym ", nativeQuery = true)
    Page<UserMyArticleResponse> getAllMyArticle(Pageable page, @Param("idUser") String idUser);

    @Query(value = """
            SELECT a.id, a.title, a.browse_date, a.tym, IF(aral.articles_id IS NULL, 0, 1) AS 'favorite',
                   GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' FROM articles a
                   LEFT JOIN articles_hashtag arha ON a.id = arha.articles_id
                   LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id
                   LEFT JOIN articles_album aral ON aral.articles_id = a.id
                   LEFT JOIN album ab ON ab.id = aral.album_id
                WHERE a.id = :id
                GROUP BY  a.id, a.title, a.browse_date, a.tym
            """, nativeQuery = true)
    Optional<UserArticleResponse> findArticleById(@Param("id") String id);
}
