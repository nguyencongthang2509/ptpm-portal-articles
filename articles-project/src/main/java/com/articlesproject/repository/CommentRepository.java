package com.articlesproject.repository;

import com.articlesproject.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(CommentRepository.NAME)
public interface CommentRepository extends JpaRepository<Comments, String> {

    public static final String NAME = "BaseCommentRepository";

    long deleteByArticlesId(String articleId);
}
