package com.articlesproject.repository;

import com.articlesproject.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(AlbumRepository.NAME)
public interface AlbumRepository extends JpaRepository<Album, String> {

    public static final String NAME = "BaseAlbumRepository";

}
