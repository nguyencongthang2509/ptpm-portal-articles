package com.articlesproject.core.censor.controller;

import com.articlesproject.core.censor.model.request.ArticleRequest;
import com.articlesproject.core.censor.model.request.UpdateStatusArticleRequest;
import com.articlesproject.core.censor.service.CensorArticleService;
import com.articlesproject.core.common.base.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/censor/article")
public class CensorArticleResController {

    @Autowired
    private CensorArticleService articleService;

    @GetMapping
    public ResponseObject getAllArticleNotApprove(final ArticleRequest request){
        return new ResponseObject(articleService.getAllArticleNotApprove(request));
    }

    @GetMapping("/{id}")
    public ResponseObject findArticleById(@PathVariable("id") String id){
        return new ResponseObject(articleService.findArticleById(id));
    }

    @PutMapping("/approve-article")
    public ResponseObject approveArticle(@RequestBody UpdateStatusArticleRequest request){
        return new ResponseObject(articleService.approveArticle(request));
    }

    @PutMapping("/refuse-article")
    public ResponseObject refuseArticle(@RequestBody UpdateStatusArticleRequest request){
        return new ResponseObject(articleService.refuseArticle(request));
    }
}
