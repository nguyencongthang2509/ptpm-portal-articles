package com.articlesproject.core.user.service;


public interface UserArticleHashtagService {

    boolean addTagsArticle(String[] hashtagId, String articleId);

    boolean updateTagsArticle(String[] hashtagId, String articleId);
}
