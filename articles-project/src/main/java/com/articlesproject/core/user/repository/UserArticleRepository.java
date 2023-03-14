package com.articlesproject.core.user.repository;


import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.entity.Articles;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserArticleRepository extends ArticlesRepository {
    @Query(value = """
            SELECT a.id, a.title, a.content, a.browse_date, a.tym FROM articles a
            """,
            countQuery = """
                    SELECT a.id, a.title, a.content, a.browse_date, a.tym FROM articles a
                    """
            , nativeQuery = true)
    Page<UserArticleResponse> getAllArticle(Pageable page, @Param("req") UserArticleRequest req);

}
