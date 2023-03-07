package com.articlesproject.core.user.repository;


import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserArticleRepository extends ArticlesRepository {
    @Query(value = "SELECT a.id, a.file_name, a.title, a.content, a.img, a.create_date, a.tym" +
                    " FROM articles a",nativeQuery = true)
    Page<UserArticleResponse> getAllArticle(Pageable page, @Param("req") UserArticleRequest req);
}
