package com.articlesproject.repository;

import com.articlesproject.entity.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(ArticlesRepository.NAME)
public interface ArticlesRepository extends JpaRepository<Articles, String> {

    public static final String NAME = "BaseArticlesRepository";

    @Query(value = """
             SELECT id,created_date,last_modified_date,browse_date,category_id,status,title,users_id FROM  articles
                                        WHERE  (UNIX_TIMESTAMP(DATE_ADD(FROM_UNIXTIME(browse_date/1000), INTERVAL 30 DAY)) > UNIX_TIMESTAMP(NOW()) * 1000) AND status = 5
            """, nativeQuery = true)
    List<Articles> getAllArticleTrashService();
}
