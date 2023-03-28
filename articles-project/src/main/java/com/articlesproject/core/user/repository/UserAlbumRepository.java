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
           SELECT ab.id, ab.title, ab.status , (select count(arab.id) from articles_album arab left join articles ar on ar.id = arab.articles_id\s
            where arab.album_id = ab.id and ar.status = 3) \s
            AS numberOfArticle, us.name AS userName, us.img AS userImg, ab.created_date FROM album ab
            LEFT JOIN users us ON us.id = ab.users_id
            WHERE (ab.users_id LIKE :userId )
            GROUP BY ab.id, ab.title, ab.status, ab.created_date, us.name , us.img
            """, nativeQuery = true)
    List<UserAlbumResponse> findAllAlbumByUserId(@Param("userId") String userId);

    @Query(value = """
           SELECT ab.id, ab.title, ab.status , (select count(arab.id) from articles_album arab left join articles ar on ar.id = arab.articles_id\s
                where arab.album_id = ab.id and ar.status = 3) \s
                AS numberOfArticle, us.name AS userName, us.img AS userImg, ab.created_date FROM album ab
                LEFT JOIN users us ON us.id = ab.users_id
                WHERE ab.users_id LIKE :userId AND ab.status = 1
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

    @Query(value = """
           SELECT ab.id, ab.title, ab.status , (select count(arab.id) from articles_album arab left join articles ar on ar.id = arab.articles_id
            where arab.album_id = ab.id and ar.status = 3)
            AS numberOfArticle, us.name AS userName, us.img AS userImg, ab.created_date FROM album ab
            LEFT JOIN users us ON us.id = ab.users_id
            WHERE (ab.users_id LIKE :userId )
            AND ab.id = :id
            GROUP BY ab.id, ab.title, ab.status, ab.created_date, us.name , us.img
            """, nativeQuery = true)
    UserAlbumResponse findByIdAndUsersId(String id, String userId);
}
