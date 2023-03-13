package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.request.CreateHashtagRequest;
import com.articlesproject.core.user.model.response.UserHashtagResponse;
import com.articlesproject.entity.Hashtag;

import java.util.List;

public interface UserHashtagService {

    List<UserHashtagResponse> getAll();

    Hashtag createHashtag(CreateHashtagRequest request);
}
