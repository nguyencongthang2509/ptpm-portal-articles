package com.articlesproject.infrastructure.configuration;

import com.articlesproject.entity.Articles;
import com.articlesproject.repository.ArticlesRepository;
import com.articlesproject.repository.Articles_AlbumRepository;
import com.articlesproject.repository.Articles_HashtagRepository;
import com.articlesproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@EnableScheduling
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class deleteArticleConfiguration {

    @Autowired
    @Qualifier("BaseArticles_CategoryRepository")
    private Articles_HashtagRepository articlesHashtagRepository;

    @Autowired
    @Qualifier("BaseArticles_AlbumRepository")
    private Articles_AlbumRepository articlesAlbumRepository;

    @Autowired
    @Qualifier("BaseArticlesRepository")
    private ArticlesRepository articlesRepository;

    @Autowired
    @Qualifier("BaseCommentRepository")
    private CommentRepository commentRepository;

    @Scheduled(cron = "0 0 3 * * ?")
    public void scheduledFixedDelayTask(){
       List<Articles> getList = articlesRepository.getAllArticleTrashService();
       getList.stream().forEach(item ->{
           articlesAlbumRepository.deleteByArticlesId(item.getId());
           articlesHashtagRepository.deleteByArticlesId(item.getId());
           commentRepository.deleteByArticlesId(item.getId());
           articlesRepository.deleteById(item.getId());
       });
    }
}
