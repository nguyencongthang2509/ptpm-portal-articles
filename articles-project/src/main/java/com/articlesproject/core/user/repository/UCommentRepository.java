package com.articlesproject.core.user.repository;

import com.articlesproject.core.user.model.response.CommentResponse;
import com.articlesproject.repository.CommentRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UCommentRepository extends CommentRepository {

    @Query(value = """
            SELECT co.id, co.content, co.reply, us.id AS userID, us.name AS userName, co.created_date FROM comments co
            JOIN articles ar ON ar.id = co.articles_id
            JOIN users us ON us.id = co.users_id
            WHERE ar.id = :articleId
            ORDER BY  co.created_date ASC
            """,nativeQuery = true)
    List<CommentResponse> findCommentByArticleId(@Param("articleId") String articleId);

    long deleteByReply(String replyId);
}
