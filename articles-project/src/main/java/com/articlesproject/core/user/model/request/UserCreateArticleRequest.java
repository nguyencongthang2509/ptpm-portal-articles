package com.articlesproject.core.user.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UserCreateArticleRequest {
    private String usersId;
    private String categoryId;
    private String title;
    private String content;
    private String img;
    private String fileName;

}
