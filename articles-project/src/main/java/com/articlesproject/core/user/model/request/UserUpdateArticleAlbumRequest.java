package com.articlesproject.core.user.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateArticleAlbumRequest {

    private String id;

    private String articlesId;
}
