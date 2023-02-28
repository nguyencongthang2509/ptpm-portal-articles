package com.articlesproject.repository;

import com.articlesproject.entity.a.BaiViet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(BaiVietRepository.NAME)
public interface BaiVietRepository extends JpaRepository<BaiViet, String> {

    public static final String NAME = "BaseBaiVietRepository";
}
