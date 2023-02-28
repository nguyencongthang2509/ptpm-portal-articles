package com.articlesproject.repository;

import com.articlesproject.entity.Diem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(DiemRepository.NAME)
public interface DiemRepository extends JpaRepository<Diem, String> {

    public static final String NAME = "BaseDiemRepository";

}
