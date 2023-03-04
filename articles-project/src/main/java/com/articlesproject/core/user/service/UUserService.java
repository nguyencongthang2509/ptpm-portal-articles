package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.respone.UserRespone;

import java.util.List;

public interface UUserService {

    List<UserRespone> findByIdUser(String id);
}
