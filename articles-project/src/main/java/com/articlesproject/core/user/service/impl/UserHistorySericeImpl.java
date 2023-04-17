package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.response.UserHistoryResponse;
import com.articlesproject.core.user.repository.UserHistoryRepository;
import com.articlesproject.core.user.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHistorySericeImpl implements UserHistoryService {

    @Autowired
    private UserHistoryRepository historyRepository;

    @Override
    public List<UserHistoryResponse> findAllHistory(String userId) {
        return historyRepository.findAllHistory(userId);
    }
}
