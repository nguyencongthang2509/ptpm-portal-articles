package com.articlesproject.core.user.model.request;

import com.articlesproject.core.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMyArticleRequest extends PageableRequest {
    private String fileName;
    private String title;
    private String content;
    private Long createDate;
    private Integer tym;
}
