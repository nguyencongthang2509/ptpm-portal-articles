package com.articlesproject.core.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserCreateArticleAlbumRequest {

    @NotEmpty
    private String articlesId;

    @NotEmpty
    private String albumId;
}
