package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.core.user.repository.UserMyArticleRepository;
import com.articlesproject.core.user.repository.UserRepository;
import com.articlesproject.core.user.service.UserMyArticleService;
import com.articlesproject.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserMyArticleServiceImpl implements UserMyArticleService {

    @Autowired
    private UserMyArticleRepository userMyArticleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PageableObject<UserMyArticleResponse> getAllMyArticle(UserMyArticleRequest request, String idUser) {
        Optional<Users> users = userRepository.findById(idUser);
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserMyArticleResponse> res = userMyArticleRepository.getAllMyArticle(pageable, users.get().getId());
        return new PageableObject<>(res);
    }
}
