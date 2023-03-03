package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.repository.UserArticleRepository;
import com.articlesproject.core.user.service.UserArticleService;
import com.articlesproject.infrastructure.projection.SimpleEntityProj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserArticleServiceImpl implements UserArticleService {

    @Autowired
    private UserArticleRepository userArticleRepository;

    @Override
    public List<UserArticleResponse> getAllArticle() {
        return userArticleRepository.getAllArticle();
    }

}
