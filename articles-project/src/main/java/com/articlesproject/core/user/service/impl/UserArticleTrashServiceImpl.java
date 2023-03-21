package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserArticleTrashRequest;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.model.response.UserArticleTrashResponse;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.core.user.repository.UserArticleHashtagRepository;
import com.articlesproject.core.user.repository.UserArticleTrashRepository;
import com.articlesproject.core.user.repository.UserMyArticleRepository;
import com.articlesproject.core.user.repository.UserRepository;
import com.articlesproject.core.user.service.UserArticleTrashService;
import com.articlesproject.core.user.service.UserMyArticleService;
import com.articlesproject.entity.Articles;
import com.articlesproject.entity.ArticlesHashtag;
import com.articlesproject.entity.Users;
import com.articlesproject.infrastructure.constant.ArticleStatus;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserArticleTrashServiceImpl implements UserArticleTrashService {

    @Autowired
    private UserArticleTrashRepository userArticleTrashRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserArticleHashtagRepository articleHashtagRepository;

    private final FormUtils formUtils = new FormUtils();


    @Override
    public PageableObject<UserArticleTrashResponse> getAllArticleTrash(final UserArticleTrashRequest request, String idUser) {
        Optional<Users> users = userRepository.findById(idUser);
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserArticleTrashResponse> res = userArticleTrashRepository.getAllArticleTrash(pageable, users.get().getId());
        return new PageableObject<>(res);
    }

    @Override
    public boolean deleteArticle(String id) {
        return false;
    }
}
