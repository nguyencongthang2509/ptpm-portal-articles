package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.response.UserCategoryRespone;
import com.articlesproject.core.user.repository.UserCategoryRepository;
import com.articlesproject.core.user.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCategoryServiceImpl implements UserCategoryService {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Cacheable(value = "allCategory")
    @Override
    public List<UserCategoryRespone> getAllCategory() {
        return userCategoryRepository.getAllCategory();
    }
}
