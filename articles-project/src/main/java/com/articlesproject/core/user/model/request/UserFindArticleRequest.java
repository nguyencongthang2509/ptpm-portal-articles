package com.articlesproject.core.user.model.request;

import com.articlesproject.core.common.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class UserFindArticleRequest extends PageableRequest {

    private String title;
    private String hashtag;
    private String albumId;
    private String category;
}
