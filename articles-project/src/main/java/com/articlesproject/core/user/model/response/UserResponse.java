package com.articlesproject.core.user.model.response;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {User.class})
public interface UserResponse {
    @Value("#{target.name}")
    String getName();

    @Value("#{target.code}")
    String getCode();

    @Value("#{target.email}")
    String getEmail();

    @Value("#{target.phone_number}")
    String getPhoneNumber();

    @Value("#{target.img}")
    String getImg();

    @Value("#{target.numberOfArticles}")
    String getNumberOfArticles();

}
