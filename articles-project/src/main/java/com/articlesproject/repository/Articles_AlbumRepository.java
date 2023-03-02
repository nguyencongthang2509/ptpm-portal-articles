package com.articlesproject.repository;

import com.articlesproject.entity.Articles_Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(Articles_AlbumRepository.NAME)
public interface Articles_AlbumRepository extends JpaRepository<Articles_Album, String> {

    public static final String NAME = "BaseArticles_AlbumRepository";
}
