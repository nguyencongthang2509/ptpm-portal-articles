package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.request.UserFindArticleRequest;
import com.articlesproject.core.user.service.UserArticleHashtagService;
import com.articlesproject.core.user.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserArticleRestController extends BaseController {

    @Value("${app.UserId}")
    private String id;

    @Autowired
    private UserArticleService userArticleService;

    @GetMapping("")
    public ResponseObject getAllArticle(final UserArticleRequest request) {
        return new ResponseObject(userArticleService.getAllArticle(request));
    }

    @GetMapping("/search")
    public ResponseObject findAllArticle(@RequestBody UserFindArticleRequest request) {
        return new ResponseObject(userArticleService.FindAllArticle(request));
    }

    @GetMapping("/{id}")
    public ResponseObject getArticleById(@PathVariable("id") String id) {
        return new ResponseObject(userArticleService.getArticleById(id));
    }
}
