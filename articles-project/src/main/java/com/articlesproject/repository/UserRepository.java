package com.articlesproject.repository;

import com.articlesproject.entity.a.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(UserRepository.NAME)
public interface UserRepository extends JpaRepository<Users, String> {

    public static final String NAME = "BaseUserRepository";

}
