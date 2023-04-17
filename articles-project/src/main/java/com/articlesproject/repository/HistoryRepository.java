package com.articlesproject.repository;

import com.articlesproject.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(HistoryRepository.NAME)
public interface HistoryRepository extends JpaRepository<History, String> {
    public static final String NAME = "BaseHistoryRepository";
}
