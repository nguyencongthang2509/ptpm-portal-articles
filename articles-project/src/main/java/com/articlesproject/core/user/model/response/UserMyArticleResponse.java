package com.articlesproject.core.user.model.response;

import com.articlesproject.entity.Articles;
import com.articlesproject.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Articles.class})
public interface UserMyArticleResponse extends IsIdentified {
    @Value("#{target.id}")
    String getId();

    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.descriptive}")
    String getDescriptive();

    @Value("#{target.browse_date}")
    String getBrowseDate();

    @Value("#{target.tym}")
    Integer getTym();

    @Value("#{target.hashtags}")
    String getHashtags();

    @Value("#{target.status}")
    Integer getStatus();

    @Value("#{target.favorite}")
    Integer getFavorite();

    @Value("#{target.users_id}")
    String getUserId();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.img}")
    String getImg();

}
