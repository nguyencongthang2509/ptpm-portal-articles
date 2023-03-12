package com.articlesproject.core.user.repository;

import com.articlesproject.core.user.model.response.AlbumResponse;
import com.articlesproject.core.user.model.response.UserArticleAlbumResponse;
import com.articlesproject.repository.AlbumRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAlbumRepository extends AlbumRepository {

    @Query(value = """
           SELECT ab.id, ab.title, ab.status , count(arab.id)  AS numberOfArticle, us.name AS userName, us.img AS userImg, ab.created_date FROM album ab
           LEFT JOIN articles_album arab ON ab.id = arab.album_id
           LEFT JOIN users us ON us.id = ab.users_id
           WHERE ab.users_id LIKE :userId
           GROUP BY ab.id, ab.title, ab.status, ab.created_date, us.name , us.img
            """, nativeQuery = true)
    List<AlbumResponse> findAllAlbumByUserId(@Param("userId") String userId);

    @Query(value = """
           SELECT ab.id, ab.title, ab.status , count(arab.id)  AS numberOfArticle, us.name AS userName, us.img AS userImg, ab.created_date FROM album ab
           LEFT JOIN articles_album arab ON ab.id = arab.album_id
           LEFT JOIN users us ON us.id = ab.users_id
           WHERE ab.users_id LIKE :userId AND ab.status = 1
           GROUP BY ab.id, ab.title, ab.status, ab.created_date, us.name , us.img
            """, nativeQuery = true)
    List<AlbumResponse> findAllAlbumPublicByUserId(@Param("userId") String userId);

    @Query(value = """
           SELECT aral.id, ar.title, us.name AS userName, us.img AS userImage, aral.created_date FROM album al
           JOIN articles_album aral ON al.id = aral.album_id
           LEFT JOIN articles ar ON ar.id = aral.articles_id
           LEFT JOIN users us ON us.id = ar.users_id\s
           WHERE al.users_id = :userId
            """, nativeQuery = true)
    List<UserArticleAlbumResponse> getAllArticleFavorite(@Param("userId") String userId);
}
