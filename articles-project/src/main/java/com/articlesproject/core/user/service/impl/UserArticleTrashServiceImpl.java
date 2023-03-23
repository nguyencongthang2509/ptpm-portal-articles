package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserArticleTrashRequest;
import com.articlesproject.core.user.model.response.UserArticleTrashResponse;
import com.articlesproject.core.user.repository.UserArticleAlbumRepository;
import com.articlesproject.core.user.repository.UserArticleHashtagRepository;
import com.articlesproject.core.user.repository.UserArticleTrashRepository;
import com.articlesproject.core.user.repository.UserRepository;
import com.articlesproject.core.user.service.UserArticleTrashService;
import com.articlesproject.entity.Articles;
import com.articlesproject.entity.Users;
import com.articlesproject.infrastructure.constant.ArticleStatus;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UserArticleTrashServiceImpl implements UserArticleTrashService {

    @Autowired
    private UserArticleTrashRepository userArticleTrashRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserArticleAlbumRepository articleAlbumRepository;

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
        Optional<Articles> articles = userArticleTrashRepository.findById(id);
        if(!articles.isPresent()){
            throw new RestApiException(Message.ERROR_UNKNOWN);
        }
        articleAlbumRepository.deleteByArticlesId(id);
        articleHashtagRepository.deleteByArticlesId(id);
        userArticleTrashRepository.deleteById(id);
        return false;
    }

    @Override
    public Articles restoreArticle(String id) {
        Optional<Articles> articles = userArticleTrashRepository.findById(id);
        if(!articles.isPresent()){
            throw new RestApiException(Message.ERROR_UNKNOWN);
        }
        articles.get().setStatus(ArticleStatus.DA_PHE_DUYET);
        return userArticleTrashRepository.save(articles.get());
    }
}
