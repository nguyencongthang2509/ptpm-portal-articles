package com.articlesproject.core.user.repository;


import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserArticleRepository extends ArticlesRepository {
    @Query(value = "SELECT a.id, a.file_name, a.title, a.content, a.create_date, a.tym" +
                    " FROM articles a",nativeQuery = true)
    List<UserArticleResponse> getAllArticle();
}
