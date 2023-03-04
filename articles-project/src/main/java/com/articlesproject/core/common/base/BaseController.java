package com.articlesproject.core.common.base;

import com.articlesproject.infrastructure.session.ArticleProjectSession;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    protected ArticleProjectSession session;
}
