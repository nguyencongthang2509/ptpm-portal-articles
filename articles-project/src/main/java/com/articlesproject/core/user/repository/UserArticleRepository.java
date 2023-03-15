package com.articlesproject.core.user.repository;


import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.model.response.UserCommentResponse;
import com.articlesproject.entity.Articles;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserArticleRepository extends ArticlesRepository {
    @Query(value = """
            SELECT a.id, a.title, a.browse_date, a.tym FROM articles a
            """,
            countQuery = """
                    SELECT a.id, a.title, a.browse_date, a.tym FROM articles a
                    """
            , nativeQuery = true)
    Page<UserArticleResponse> getAllArticle(Pageable page, @Param("req") UserArticleRequest req);

    @Query(value = """
            SELECT a.id, a.title, a.browse_date, a.tym FROM articles a
            WHERE a.id = :id
            """,nativeQuery = true)
    Optional<UserArticleResponse> findArticleById(@Param("id") String id);
}
