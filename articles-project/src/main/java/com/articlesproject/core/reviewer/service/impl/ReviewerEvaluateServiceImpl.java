package com.articlesproject.core.reviewer.service.impl;

import com.articlesproject.core.reviewer.model.request.CreateEvaluateRequest;
import com.articlesproject.core.reviewer.model.response.EvaluateRespone;
import com.articlesproject.core.reviewer.repository.ReviewerEvaluateRepository;
import com.articlesproject.core.reviewer.service.ReviewerEvaluateService;
import com.articlesproject.entity.Evaluate;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewerEvaluateServiceImpl implements ReviewerEvaluateService {

    @Autowired
    private ReviewerEvaluateRepository evaluateRepository;

    private FormUtils formUtils = new FormUtils();


    @Override
    public List<EvaluateRespone> getAllEvaluateByArticleId(String articleId) {
        return evaluateRepository.getAllEvaluateByArticleId(articleId);
    }

    @Override
    public List<EvaluateRespone> getAllEvaluateByUserId(String userId) {
        return evaluateRepository.getAllEvaluateByUserId(userId);
    }

    @Override
    public Evaluate create(CreateEvaluateRequest request, String userId) {
        Evaluate evaluate = formUtils.convertToObject(Evaluate.class, request);
        evaluate.setUsersId(userId);
        return evaluateRepository.save(evaluate);
    }
}
