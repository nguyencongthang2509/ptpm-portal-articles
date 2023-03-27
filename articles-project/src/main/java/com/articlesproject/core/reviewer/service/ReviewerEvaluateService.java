package com.articlesproject.core.reviewer.service;

import com.articlesproject.core.reviewer.model.request.CreateEvaluateRequest;
import com.articlesproject.core.reviewer.model.response.EvaluateRespone;
import com.articlesproject.entity.Evaluate;
import java.util.List;

public interface ReviewerEvaluateService {

    List<EvaluateRespone> getAllEvaluateByArticleId(String articleId);

    List<EvaluateRespone> getAllEvaluateByUserId(String userId);

    Evaluate create(CreateEvaluateRequest request, String userId);
}
