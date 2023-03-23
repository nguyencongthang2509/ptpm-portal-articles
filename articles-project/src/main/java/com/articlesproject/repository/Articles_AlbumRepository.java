package com.articlesproject.repository;

import com.articlesproject.entity.ArticlesAlbum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(Articles_AlbumRepository.NAME)
public interface Articles_AlbumRepository extends JpaRepository<ArticlesAlbum, String> {

    public static final String NAME = "BaseArticles_AlbumRepository";

    long deleteByArticlesId(String articleId);

}
