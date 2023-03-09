package com.articlesproject.core.reviewer.repository;

import com.articlesproject.core.reviewer.model.response.EvaluateRespone;
import com.articlesproject.repository.EvalueteRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewerEvaluateRepository extends EvalueteRepository {

    @Query(value = """
            SELECT ev.id, ev.content, ev.created_date, ev.star, us.name AS userName, us.img AS userImg FROM  evaluate ev
            LEFT JOIN users us ON us.id = ev.users_id
            WHERE ev.articles_id = :articleId
            ORDER BY ev.created_date DESC
            """, nativeQuery = true)
    List<EvaluateRespone> getAllEvaluateByArticleId(@Param("articleId") String articleId);

    @Query(value = """
            SELECT ev.id, ev.content, ev.created_date, ev.star, us.name AS userName, us.img AS userImg FROM  evaluate ev
            LEFT JOIN users us ON us.id = ev.users_id
            WHERE ev.users_id = :userId
            ORDER BY ev.created_date DESC
            """, nativeQuery = true)
    List<EvaluateRespone> getAllEvaluateByUserId(@Param("userId") String userId);
}
