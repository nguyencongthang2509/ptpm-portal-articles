package com.articlesproject.core.user.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public final class UserCreateArticleRequest {
    private String usersId;
    private String categoryId;
    private String title;
    MultipartFile file;
}
