package com.articlesproject.core.censor.service.impl;

import com.articlesproject.core.censor.model.request.CreateEvaluateRequest;
import com.articlesproject.core.censor.repository.CensorEvaluateRepository;
import com.articlesproject.core.censor.service.CensorEvaluateService;
import com.articlesproject.entity.Evaluate;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CensorEvaluateServiceImpl implements CensorEvaluateService {

    @Autowired
    private CensorEvaluateRepository evaluateRepository;

    private FormUtils formUtils = new FormUtils();


    @Override
    public Evaluate create(CreateEvaluateRequest request) {
        Evaluate evaluate = formUtils.convertToObject(Evaluate.class, request);
        return evaluateRepository.save(evaluate);
    }
}
