package com.articlesproject.core.user.model.response;

import com.articlesproject.entity.Hashtag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Hashtag.class})
public interface UserHashtagResponse  {

    @Value("#{target.title}")
    String getTitle();

}
