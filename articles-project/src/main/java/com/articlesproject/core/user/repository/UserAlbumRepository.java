package com.articlesproject.core.user.repository;

import com.articlesproject.core.user.model.response.SimpleAlbumProjRequest;
import com.articlesproject.core.user.model.response.UserAlbumResponse;
import com.articlesproject.core.user.model.response.UserArticleAlbumResponse;
import com.articlesproject.entity.Album;
import com.articlesproject.repository.AlbumRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAlbumRepository extends AlbumRepository {

    @Query(value = """
           SELECT ab.id, ab.title, ab.status , count(arab.id)  AS numberOfArticle, us.name AS userName, us.img AS userImg, ab.created_date FROM album ab
           LEFT JOIN articles_album arab ON ab.id = arab.album_id
           LEFT JOIN users us ON us.id = ab.users_id
           LEFT JOIN articles ar ON ar.id = arab.articles_id
           WHERE (ab.users_id LIKE :userId ) AND ab.status != 5
           GROUP BY ab.id, ab.title, ab.status, ab.created_date, us.name , us.img
            """, nativeQuery = true)
    List<UserAlbumResponse> findAllAlbumByUserId(@Param("userId") String userId);

    @Query(value = """
           SELECT ab.id, ab.title, ab.status , count(arab.id)  AS numberOfArticle, us.name AS userName, us.img AS userImg, ab.created_date FROM album ab
           LEFT JOIN articles_album arab ON ab.id = arab.album_id
           LEFT JOIN users us ON us.id = ab.users_id
           LEFT JOIN articles ar ON ar.id = arab.articles_id
           WHERE ab.users_id LIKE :userId AND ab.status = 3
           GROUP BY ab.id, ab.title, ab.status, ab.created_date, us.name , us.img
            """, nativeQuery = true)
    List<UserAlbumResponse> findAllAlbumPublicByUserId(@Param("userId") String userId);

    @Query(value = """
           SELECT ab.id, ab.title, ab.status, (SELECT  COUNT(articles_album.id) FROM articles_album WHERE articles_album.articles_id = :articleId AND  articles_album.album_id = ab.id ) AS 'countArticle' FROM album ab
           LEFT JOIN articles_album arab ON ab.id = arab.album_id
           LEFT JOIN users us ON us.id = ab.users_id
           WHERE ab.users_id LIKE :userId 
           GROUP BY ab.id, ab.title, ab.status
           ORDER BY ab.created_date DESC 
            """, nativeQuery = true)
    List<SimpleAlbumProjRequest> findAllSimpleAllBumByUserId(@Param("userId") String userId, @Param("articleId") String articleId);

    Album findByIdAndUsersId(String id, String userId);
}
