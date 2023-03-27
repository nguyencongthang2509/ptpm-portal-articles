package com.articlesproject.core.user.model.response;

import com.articlesproject.entity.Articles;
import com.articlesproject.entity.Tyms;
import com.articlesproject.entity.Users;
import com.articlesproject.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Articles.class, Users.class, Tyms.class})
public interface UserTymResponse extends IsIdentified {

    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.userName}")
    String getUserName();

    @Value("#{target.userImage}")
    String getUserImage();

    @Value("#{target.articleId}")
    String getArticleId();

    @Value("#{target.created_date}")
    String getCreatedDate();
}
