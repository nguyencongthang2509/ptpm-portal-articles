package com.articlesproject.core.common.base;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCommentObject {
    private Object user;
    private Object comment;
}
