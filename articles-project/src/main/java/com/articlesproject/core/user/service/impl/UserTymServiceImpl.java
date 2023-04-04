package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.request.UserCreateTymRequest;
import com.articlesproject.core.user.model.response.UserTymResponse;
import com.articlesproject.core.user.repository.UserArticleRepository;
import com.articlesproject.core.user.repository.UserTymRepository;
import com.articlesproject.core.user.service.UserTymService;
import com.articlesproject.entity.Tyms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UserTymServiceImpl implements UserTymService {

    @Autowired
    private UserTymRepository tymRepository;

    @Autowired
    private UserArticleRepository articleRepository;

    @Override
    public List<UserTymResponse> getAllArticleFavorite(String userId) {
        return tymRepository.getAllArticleFavorite(userId);
    }

    @Override
    public Tyms favoriteArticle(String userId, UserCreateTymRequest request) {
        Tyms tyms = new Tyms();
        tyms.setArticleId(request.getArticlesId());
        tyms.setUsersId(userId);
        return tymRepository.save(tyms);
    }

    @Override
    public boolean unFavoriteArticle(String userId, String articleId) {
        tymRepository.deleteByUsersIdAndArticleId(userId, articleId);
        return true;
    }
}
