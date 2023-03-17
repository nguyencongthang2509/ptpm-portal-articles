package com.articlesproject.core.user.model.request;

import com.articlesproject.core.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserArticleRequest extends PageableRequest {

    private String userId;
    private String title;
    private String content;
    private Long browseDate;
    private String img;
    private Integer tym;
}
