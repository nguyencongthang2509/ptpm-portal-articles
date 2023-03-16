package com.articlesproject.core.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateArticleAlbumRequest {

    private String id;

    private String articlesId;
}
