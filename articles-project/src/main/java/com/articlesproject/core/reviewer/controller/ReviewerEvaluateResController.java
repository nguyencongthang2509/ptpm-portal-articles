package com.articlesproject.core.reviewer.controller;

import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.reviewer.model.request.CreateEvaluateRequest;
import com.articlesproject.core.reviewer.service.ReviewerEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/reviewer/api/evaluate")
public class ReviewerEvaluateResController {

    @Value("${app.UserId}") private String id;

    @Autowired
    private ReviewerEvaluateService evaluateService;

    @GetMapping("/evaluate-by-article/{articleId}")
    private ResponseObject getAllEvaluateByArticleId(@PathVariable("articleId") String articleId){
        return  new ResponseObject(evaluateService.getAllEvaluateByArticleId(articleId));
    }

    @GetMapping("/evaluate-by-user/{userId}")
    private ResponseObject getAllEvaluateByUserId(@PathVariable("userId") String userId){
        return  new ResponseObject(evaluateService.getAllEvaluateByUserId(userId));
    }

    @PostMapping("/create")
    private ResponseObject createEvaluate(@RequestBody CreateEvaluateRequest request){
        String userId = id;
        return  new ResponseObject(evaluateService.create(request, userId));
    }
}
