package com.articlesproject.core.user.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UserUpdateArticleRequest {

    private String id;
    private String categoryId;
    private String title;
    private String content;
    private String descriptive;
    private String[] hashtag;

}
