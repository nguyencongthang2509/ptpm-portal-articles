package com.articlesproject.repository;

import com.articlesproject.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(HashtagRepository.NAME)
public interface HashtagRepository extends JpaRepository<Hashtag, String> {
    public static final String NAME = "BaseHashtagRepository";
}
