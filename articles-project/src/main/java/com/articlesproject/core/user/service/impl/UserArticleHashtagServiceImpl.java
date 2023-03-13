package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.repository.UserArticleHashtagRepository;
import com.articlesproject.core.user.service.UserArticleHashtagService;
import com.articlesproject.entity.ArticlesHashtag;
import com.articlesproject.entity.Hashtag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public  class UserArticleHashtagServiceImpl implements UserArticleHashtagService {

    @Autowired
    private UserArticleHashtagRepository articleHashtagRepository;


    @Override
    public boolean addTagsArticle(String[] hashtagId, String articleId) {
        Arrays.stream(hashtagId).forEach(item ->{
            ArticlesHashtag articlesHashtag = new ArticlesHashtag();
            articlesHashtag.setArticlesId(articleId);
            articlesHashtag.setHashtagId(item);
            articleHashtagRepository.save(articlesHashtag);
        });
        return true;
    }
}
