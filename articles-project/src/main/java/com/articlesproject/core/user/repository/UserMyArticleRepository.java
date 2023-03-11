package com.articlesproject.core.user.repository;


import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.repository.ArticlesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMyArticleRepository extends ArticlesRepository {
    @Query(value = "SELECT a.id, a.file_name, a.title, a.content, a.img, a.create_date, a.tym \n" +
                    "FROM articles a join users u on a.users_id = u.id where a.users_id = :idUser",nativeQuery = true)
    Page<UserMyArticleResponse> getAllMyArticle(Pageable page, @Param("idUser") String idUser);
}
