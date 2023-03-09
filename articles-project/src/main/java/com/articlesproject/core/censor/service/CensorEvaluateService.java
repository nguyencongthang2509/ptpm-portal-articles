package com.articlesproject.core.censor.service;

import com.articlesproject.core.censor.model.request.CreateEvaluateRequest;
import com.articlesproject.entity.Evaluate;

public interface CensorEvaluateService {

    Evaluate create(CreateEvaluateRequest request);
}
