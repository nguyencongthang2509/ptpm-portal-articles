package com.articlesproject.infrastructure.session;

import com.articlesproject.infrastructure.constant.SessionConstant;
import com.articlesproject.infrastructure.projection.SimpleEntityProj;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class ArticleProjectSessionImpl implements ArticleProjectSession {

    @Autowired
    private HttpSession session;

    @Override
    public String getUserId() {
        SimpleEntityProj account = (SimpleEntityProj) session.getAttribute(SessionConstant.USER);
        return account.getId();
    }
}
