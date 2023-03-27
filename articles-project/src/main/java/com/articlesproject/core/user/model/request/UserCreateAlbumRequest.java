package com.articlesproject.core.user.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateAlbumRequest {

    @NotEmpty
    @Size(min = 2, max = 250)
    private String title;

    @NotNull
    private boolean status;

}
