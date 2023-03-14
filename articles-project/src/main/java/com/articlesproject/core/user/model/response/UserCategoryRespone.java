package com.articlesproject.core.user.model.response;

import com.articlesproject.entity.Category;
import com.articlesproject.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Category.class})
public interface UserCategoryRespone extends IsIdentified {

    @Value("#{target.id}")
    String getId();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.name}")
    String getName();
}
