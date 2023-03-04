package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.common.base.PageableRequest;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.repository.UserArticleRepository;
import com.articlesproject.core.user.service.UserArticleService;
import com.articlesproject.infrastructure.projection.SimpleEntityProj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserArticleServiceImpl implements UserArticleService {

    @Autowired
    private UserArticleRepository userArticleRepository;

    @Override
    public PageableObject<UserArticleResponse> getAllArticle(final UserArticleRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserArticleResponse> res = userArticleRepository.getAllArticle(pageable, request);
        return new PageableObject<>(res);
    }

}
