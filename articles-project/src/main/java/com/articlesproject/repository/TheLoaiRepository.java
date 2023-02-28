package com.articlesproject.repository;

import com.articlesproject.entity.TheLoai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(TheLoaiRepository.NAME)
public interface TheLoaiRepository extends JpaRepository<TheLoai, String> {

    public static final String NAME = "BaseTheLoaiRepository";
}
