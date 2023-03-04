package com.articlesproject.core.user.repository;

import com.articlesproject.core.user.model.respone.AlbumRespone;
import com.articlesproject.entity.Album;
import com.articlesproject.repository.AlbumRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UAlbumRepository extends AlbumRepository {

    @Query(value = """
           SELECT ab.id, ab.title , count(arab.id)  AS soLuong, ab.created_date FROM album ab
           LEFT JOIN articles_album arab ON ab.id = arab.album_id
           WHERE ab.users_id LIKE :userId
           GROUP BY ab.id, ab.title, ab.created_date
            """, nativeQuery = true)
    List<AlbumRespone> findAllAlbumByUserId(@Param("userId") String userId);
}
