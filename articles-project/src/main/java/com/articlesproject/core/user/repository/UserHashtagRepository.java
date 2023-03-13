package com.articlesproject.core.user.repository;

import com.articlesproject.core.user.model.response.UserHashtagResponse;
import com.articlesproject.repository.HashtagRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserHashtagRepository extends HashtagRepository {

    @Query(value = """
            SELECT id, title FROM hashtag
            ORDER BY created_date DESC
            """, nativeQuery = true)
    List<UserHashtagResponse> getAll();
}
