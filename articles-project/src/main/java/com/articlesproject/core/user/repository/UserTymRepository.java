package com.articlesproject.core.user.repository;

import com.articlesproject.core.user.model.response.UserTymResponse;
import com.articlesproject.repository.TymRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTymRepository extends TymRepository {

    @Query(value = """
           SELECT ty.id, ar.title, us.name AS userName, ar.id AS articleId, us.img AS userImage, ty.created_date  FROM tyms ty
           LEFT JOIN articles ar ON ar.id = ty.article_id
           LEFT JOIN users us ON us.id = ar.users_id
           WHERE ty.users_id = :userId
            """, nativeQuery = true)
    List<UserTymResponse> getAllArticleFavorite(@Param("userId") String userId);

    long deleteByUsersIdAndArticleId(String userId, String articleId);
}
