package com.articlesproject.core.user.repository;

import com.articlesproject.core.user.model.response.UserHistoryResponse;
import com.articlesproject.entity.History;
import com.articlesproject.repository.HistoryRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserHistoryRepository extends HistoryRepository {

    @Query(value = """
            SELECT h.id, h.articles_id, h.users_id, h.create_at, h.created_date, h.last_modified_date
            FROM history h WHERE h.articles_id = :articlesId AND h.users_id = :usersId
            """, nativeQuery = true)
    Optional<History> findHistoriesByArticlesIdAndUsersId(@Param("articlesId") String articlesId, @Param("usersId") String usersId);

    @Query(value = """
            SELECT hi.id, hi.articles_id, hi.users_id, ar.title, ar.descriptive, ar.browse_date, ar.status, us.img, us.name, hi.create_at, hi.last_modified_date from history hi
            LEFT JOIN articles ar on hi.articles_id = ar.id 
            LEFT JOIN users us on hi.users_id = us.id
            WHERE ar.users_id = :usersId
            GROUP BY  hi.id, hi.articles_id, hi.users_id, ar.title, ar.descriptive, ar.browse_date, ar.status, us.img, us.name, hi.create_at, hi.last_modified_date
            ORDER BY hi.last_modified_date DESC
            """, nativeQuery = true)
    List<UserHistoryResponse> findAllHistory(@Param("usersId") String usersId);
}
