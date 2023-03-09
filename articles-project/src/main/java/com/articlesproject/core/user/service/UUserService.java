package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.response.UserResponse;

import java.util.List;

public interface UUserService {

    List<UserResponse> findByIdUser(String id);
}
