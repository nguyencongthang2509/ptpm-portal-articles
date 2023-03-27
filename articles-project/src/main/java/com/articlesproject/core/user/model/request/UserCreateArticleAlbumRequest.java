package com.articlesproject.core.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateArticleAlbumRequest {

    @NotEmpty
    private String articlesId;

    @NotEmpty
    private String albumId;
}
