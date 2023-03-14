package com.articlesproject.core.user.repository;

import com.articlesproject.core.user.model.response.UserCategoryRespone;
import com.articlesproject.repository.CategoryRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCategoryRepository extends CategoryRepository {
    @Query(value = """
            SELECT ca.id, ca.code, ca.name
            FROM category ca
            """,nativeQuery = true)
    List<UserCategoryRespone> getAllCategory();
}
