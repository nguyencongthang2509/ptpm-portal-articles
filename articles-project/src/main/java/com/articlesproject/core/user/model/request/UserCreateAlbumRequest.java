package com.articlesproject.core.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateAlbumRequest {

    @NotEmpty
    @Size(min = 2)
    private String title;

    private boolean status;

}
