package com.articlesproject.repository;

import com.articlesproject.entity.Evaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(EvalueteRepository.NAME)
public interface EvalueteRepository extends JpaRepository<Evaluate, String> {
    public static final String NAME = "BaseEvalueteRepository";

}
