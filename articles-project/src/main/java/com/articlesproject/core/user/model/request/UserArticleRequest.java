package com.articlesproject.core.user.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserArticleRequest {
    private String fileName;
    private String title;
    private String content;
    private Long createDate;
    private Integer tym;
}
