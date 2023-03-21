package com.articlesproject.core.user.repository;


import com.articlesproject.core.user.model.response.UserArticleTrashResponse;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserArticleTrashRepository extends ArticlesRepository {
    @Query(value = "SELECT a.id, a.title, a.browse_date, a.tym, a.status, \n" +
            "GROUP_CONCAT(ha.title ORDER BY ha.title SEPARATOR ', ') AS 'hashtags' FROM articles a\n" +
            "            LEFT JOIN articles_hashtag  arha ON a.id = arha.articles_id\n" +
            "            LEFT JOIN hashtag ha ON ha.id = arha.hashtag_id where a.users_id = :idUser AND a.status = 5" +
            " GROUP BY  a.id, a.title, a.browse_date, a.tym, a.status ", nativeQuery = true)
    Page<UserArticleTrashResponse> getAllArticleTrash(Pageable page, @Param("idUser") String idUser);
}
