package com.articlesproject.core.user.repository;


import com.articlesproject.core.user.model.response.UserArticleTrashResponse;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserArticleTrashRepository extends ArticlesRepository {
    @Query(value = """
             SELECT ar.id, ar.title, ar.browse_date, ar.status
                            FROM articles ar
                            WHERE ar.users_id = :userId AND ar.status = 5
                            GROUP BY  ar.id, ar.title, ar.browse_date
                            ORDER BY ar.browse_date DESC
            """,countQuery = """
             SELECT COUNT(ar.id)
                            FROM articles ar
                            WHERE ar.users_id = :userId AND ar.status = 5
                            GROUP BY  ar.id, ar.title, ar.browse_date, ar.status
                            ORDER BY ar.browse_date DESC
            """,nativeQuery = true)
    Page<UserArticleTrashResponse> getAllArticleTrash(Pageable page, @Param("userId") String userId);
}
