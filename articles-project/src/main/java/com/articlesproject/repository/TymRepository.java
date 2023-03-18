package com.articlesproject.repository;

import com.articlesproject.entity.Tyms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TymRepository extends JpaRepository<Tyms, String> {
}
