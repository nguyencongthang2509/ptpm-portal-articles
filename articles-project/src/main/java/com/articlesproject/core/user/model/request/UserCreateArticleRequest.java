package com.articlesproject.core.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public final class UserCreateArticleRequest {

    @NotEmpty
    @Size(min = 6, max = 250)
    private String title;

    @NotEmpty
    private String content;

    private String descriptive;

    private String[] hashtag;
}
