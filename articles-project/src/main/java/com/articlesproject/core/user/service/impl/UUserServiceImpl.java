package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.response.UserResponse;
import com.articlesproject.core.user.repository.UUserRepository;
import com.articlesproject.core.user.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UUserServiceImpl implements UUserService {

    @Autowired
    private UUserRepository userRepository;

    @Override
    public List<UserResponse> findByIdUser(String id) {
        return userRepository.findByIdUser(id);
    }

    @Override
    public List<UserResponse> findByIdAuthor(String id) {
        return userRepository.findByIdAuthor(id);
    }
}
