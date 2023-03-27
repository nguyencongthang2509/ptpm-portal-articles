package com.articlesproject.core.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateAlbumRequest {

    @NotEmpty
    private String id;

    @NotEmpty
    @Size(min = 2)
    private String title;

    private boolean status;

}
