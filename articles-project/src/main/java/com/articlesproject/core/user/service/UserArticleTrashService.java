package com.articlesproject.core.user.service;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserArticleTrashRequest;
import com.articlesproject.core.user.model.response.UserArticleTrashResponse;
import com.articlesproject.entity.Articles;

import java.io.IOException;

public interface UserArticleTrashService {
    PageableObject<UserArticleTrashResponse> getAllArticleTrash(final UserArticleTrashRequest request, String idUser);

    boolean deleteArticle(String id);

    Articles restoreArticle(String id);
}
