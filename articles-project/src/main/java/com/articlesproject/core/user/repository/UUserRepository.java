package com.articlesproject.core.user.repository;

import com.articlesproject.core.user.model.response.UserResponse;
import com.articlesproject.repository.UserRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UUserRepository extends UserRepository {

    @Query(value = """
            SELECT us.name, us.email, us.code, us.phone_number, us.img, COUNT(ar.users_id) AS numberOfArticles  
            FROM users us
            LEFT JOIN articles ar ON ar.users_id = us.id
            WHERE us.id LIKE :id
            GROUP BY us.name, us.email, us.phone_number,us.img
            """, nativeQuery = true)
    List<UserResponse> findByIdUser(@Param("id")String id);

    @Query(value = """
            SELECT us.name, us.email, us.code, us.phone_number, us.img, COUNT(ar.users_id) AS numberOfArticles  
            FROM users us
            LEFT JOIN articles ar ON ar.users_id = us.id
            WHERE us.id LIKE :id AND ar.status = 3
            GROUP BY us.name, us.email, us.phone_number,us.img
            """, nativeQuery = true)
    List<UserResponse> findByIdAuthor(@Param("id")String id);
}
