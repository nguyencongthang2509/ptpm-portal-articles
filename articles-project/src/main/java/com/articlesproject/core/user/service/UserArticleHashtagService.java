package com.articlesproject.core.user.service;

import com.articlesproject.entity.ArticlesHashtag;
import com.articlesproject.entity.Hashtag;

public interface UserArticleHashtagService {

    boolean addTagsArticle(String[] hashtagId, String articleId);

    boolean updateTagsArticle(String[] hashtagId, String articleId);
}
