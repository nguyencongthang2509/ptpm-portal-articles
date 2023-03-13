package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.response.UserHashtagResponse;
import com.articlesproject.core.user.repository.UserHashtagRepository;
import com.articlesproject.core.user.service.UserHashtagService;
import com.articlesproject.entity.Hashtag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHashtagServiceImpl implements UserHashtagService {

    @Autowired
    private UserHashtagRepository hashtagRepository;

    @Override
    public List<UserHashtagResponse> getAll() {
        return hashtagRepository.getAll();
    }
}
