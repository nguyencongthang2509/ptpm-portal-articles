package com.articlesproject.core.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateAlbumRequest {

    @NotEmpty
    @Size(min = 2)
    private String title;

    @NotNull
    private boolean status;

}
