package com.articlesproject.core.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class UserUpdateArticleRequest {

    private String id;
    private String categoryId;
    private String title;
    private String content;
    private String img;
    private String[] hashtag;
}
