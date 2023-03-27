package com.articlesproject.core.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateHashtagRequest {

    @NotEmpty
    private String title;
}
