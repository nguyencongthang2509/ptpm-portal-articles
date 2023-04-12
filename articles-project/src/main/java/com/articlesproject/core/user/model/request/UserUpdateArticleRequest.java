package com.articlesproject.core.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class UserUpdateArticleRequest {

    private String id;
    private String title;
    private String content;
    private String descriptive;
    private String[] hashtag;
    private int status;
}
