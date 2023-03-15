package com.articlesproject.core.user.model.response;

import com.articlesproject.entity.Articles;
import com.articlesproject.entity.Hashtag;
import com.articlesproject.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Articles.class, Hashtag.class})
public interface UserGetArticleById extends IsIdentified {
    @Value("#{target.id}")
    String getId();

    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.browse_date}")
    Long getBrowseDate();

    @Value("#{target.tym}")
    Integer getTym();

    @Value("#{taget.name}")
    String getName();
}
