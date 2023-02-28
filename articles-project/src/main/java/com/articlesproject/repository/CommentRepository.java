package com.articlesproject.repository;

import com.articlesproject.entity.a.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(CommentRepository.NAME)
public interface CommentRepository extends JpaRepository<Comments, String> {

    public static final String NAME = "BaseCommentRepository";


}
