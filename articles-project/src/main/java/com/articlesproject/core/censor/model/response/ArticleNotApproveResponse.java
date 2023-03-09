package com.articlesproject.core.censor.model.response;

import com.articlesproject.entity.Articles;
import com.articlesproject.entity.Users;
import com.articlesproject.entity.base.IsIdentified;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Articles.class, Users.class, Category.class})
public interface ArticleNotApproveResponse extends IsIdentified {

    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.content}")
    String getContent();

    @Value("#{target.created_date}")
    String getCreatedDate();

    @Value("#{target.userName}")
    String getUserName();

    @Value("#{target.userCode}")
    String getUserCode();

    @Value("#{target.categoryName}")
    String getCategoryName();

    @Value("#{target.categoryCode}")
    String getCategoryCode();
}
