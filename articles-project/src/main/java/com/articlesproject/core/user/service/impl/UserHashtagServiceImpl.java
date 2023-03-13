package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.request.CreateHashtagRequest;
import com.articlesproject.core.user.model.response.UserHashtagResponse;
import com.articlesproject.core.user.repository.UserHashtagRepository;
import com.articlesproject.core.user.service.UserHashtagService;
import com.articlesproject.entity.Hashtag;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UserHashtagServiceImpl implements UserHashtagService {

    @Autowired
    private UserHashtagRepository hashtagRepository;

    private FormUtils formUtils = new FormUtils();

    @Override
    public List<UserHashtagResponse> getAll() {
        return hashtagRepository.getAll();
    }

    @Override
    public Hashtag createHashtag(CreateHashtagRequest request) {
        Hashtag hashtag = hashtagRepository.findByTitle(request.getTitle().toLowerCase());
        if(hashtag != null){
            return hashtag;
        }
        Hashtag newhashtag = formUtils.convertToObject(Hashtag.class, request);
        return hashtagRepository.save(newhashtag);
    }
}
