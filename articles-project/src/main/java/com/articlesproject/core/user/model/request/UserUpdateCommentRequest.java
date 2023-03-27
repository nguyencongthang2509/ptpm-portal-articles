package com.articlesproject.core.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateCommentRequest {

    @NotEmpty
    private String id;

    @NotEmpty
    @Size(min = 6)
    private String content;

}
