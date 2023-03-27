package com.articlesproject.repository;

import com.articlesproject.entity.ArticlesAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(ArticlesAlbumRepository.NAME)
public interface ArticlesAlbumRepository extends JpaRepository<ArticlesAlbum, String> {

    public static final String NAME = "BaseArticlesAlbumRepository";

    long deleteByArticlesId(String articleId);

}
