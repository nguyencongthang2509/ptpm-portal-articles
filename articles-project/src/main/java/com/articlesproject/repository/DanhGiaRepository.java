package com.articlesproject.repository;

import com.articlesproject.entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(DanhGiaRepository.NAME)
public interface DanhGiaRepository extends JpaRepository<DanhGia, String> {
    public static final String NAME = "BaseDanhGiaRepository";

}
