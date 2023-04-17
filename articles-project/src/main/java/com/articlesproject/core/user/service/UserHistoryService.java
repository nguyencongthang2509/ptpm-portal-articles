package com.articlesproject.core.user.service;

import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserFindHistoryRequest;
import com.articlesproject.core.user.model.response.UserHistoryResponse;

import java.util.List;

public interface UserHistoryService {
    List<UserHistoryResponse> findAllHistory(String userId);
}
