package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.repository.UserArticleHashtagRepository;
import com.articlesproject.core.user.repository.UserHashtagRepository;
import com.articlesproject.core.user.service.UserArticleHashtagService;
import com.articlesproject.entity.ArticlesHashtag;
import com.articlesproject.entity.Hashtag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserArticleHashtagServiceImpl implements UserArticleHashtagService {

    @Autowired
    private UserArticleHashtagRepository articleHashtagRepository;

    @Autowired
    private UserHashtagRepository hashtagRepository;


    @Override
    public boolean addTagsArticle(String[] hashtags, String articleId) {
        Arrays.stream(hashtags).forEach(item -> {
            Hashtag hashtag = hashtagRepository.findByTitle(item.toLowerCase());
            ArticlesHashtag articlesHashtag = new ArticlesHashtag();
            if (hashtag != null) {
                articlesHashtag.setArticlesId(articleId);
                articlesHashtag.setHashtagId(hashtag.getId());
                articleHashtagRepository.save(articlesHashtag);
            } else {
                Hashtag newhashtag = new Hashtag();
                newhashtag.setTitle(item);
                hashtagRepository.save(newhashtag);
                articlesHashtag.setArticlesId(articleId);
                articlesHashtag.setHashtagId(newhashtag.getId());
                articleHashtagRepository.save(articlesHashtag);
            }
        });
        return true;
    }

    @Override
    public boolean updateTagsArticle(String[] hashtags, String articleId) {
        List<ArticlesHashtag> currentArticlesHashtags = articleHashtagRepository.findByArticlesId(articleId);

        if (currentArticlesHashtags == null) {
            return false;
        }
        try {
            List<String> newHashtags = new ArrayList<>(Arrays.asList(hashtags));
            currentArticlesHashtags.stream()
                    .filter(current -> !newHashtags.contains(current.getHashtagId()))
                    .forEach(current -> articleHashtagRepository.delete(current));
            newHashtags.forEach(title -> {
                Hashtag hashtag = hashtagRepository.findByTitle(title.toLowerCase());
                ArticlesHashtag articlesHashtag = new ArticlesHashtag();
                articlesHashtag.setArticlesId(articleId);
                if (hashtag != null) {
                    articlesHashtag.setHashtagId(hashtag.getId());
                } else {
                    Hashtag newHashtag = new Hashtag();
                    newHashtag.setTitle(title);
                    hashtagRepository.save(newHashtag);
                    articlesHashtag.setHashtagId(newHashtag.getId());
                }
                articleHashtagRepository.save(articlesHashtag);
            });
        } catch (NullPointerException e) {
//            e.printStackTrace();
        }
        return true;
    }
}
