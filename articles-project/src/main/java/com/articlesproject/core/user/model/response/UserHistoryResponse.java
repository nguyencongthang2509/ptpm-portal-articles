package com.articlesproject.core.user.model.response;

import com.articlesproject.entity.Articles;
import com.articlesproject.entity.History;
import com.articlesproject.entity.Users;
import com.articlesproject.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {History.class, Articles.class, Users.class})
public interface UserHistoryResponse extends IsIdentified {

    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.descriptive}")
    String getDescriptive();

    @Value("#{target.browse_date}")
    Long getBrowseDate();

    @Value("#{target.status}")
    Integer getStatus();

    @Value("#{target.users_id}")
    String getUsersId();

    @Value("#{target.articles_id}")
    String getArticlesId();

    @Value("#{target.name}")
    String getName();

    @Value("#{target.img}")
    String getImg();

    @Value("#{target.create_at}")
    Long getCreateAt();

    @Value("#{target.last_modified_date}")
    Long getLastModifiedDate();
}
