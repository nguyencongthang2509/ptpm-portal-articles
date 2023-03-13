package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.response.UserHashtagResponse;

import java.util.List;

public interface UserHashtagService {

    List<UserHashtagResponse> getAll();
}
