package com.articlesproject.core.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public final class UserCreateArticleRequest {

    @NotNull
    @NotEmpty
    private String usersId;

    @NotNull
    @NotEmpty
    private String categoryId;

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String content;

    private String[] hashtag;
}
