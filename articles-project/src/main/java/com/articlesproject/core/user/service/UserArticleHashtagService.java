package com.articlesproject.core.user.service;

import com.articlesproject.entity.ArticlesHashtag;

public interface UserArticleHashtagService {

    boolean addTagsArticle(String[] hashtagId, String articleId);
}
