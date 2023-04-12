package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.UserFindArticleAuthorRequest;
import com.articlesproject.core.user.model.request.UserFindArticleByCategoryRequest;
import com.articlesproject.core.user.model.request.UserFindArticleRequest;
import com.articlesproject.core.user.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserArticleRestController extends BaseController {

    @Value("${app.UserId}")
    private String userId;

    @Autowired
    private UserArticleService userArticleService;


    @GetMapping("")
    public ResponseObject findAllArticle(final UserFindArticleRequest request) {
        return new ResponseObject(userArticleService.findAllArticle(userId,request));
    }

    @GetMapping("/search")
    public ResponseObject findArticleBySearch(final UserFindArticleRequest request) {
        return new ResponseObject(userArticleService.findAllArticle(userId,request));
    }

    @GetMapping("/find-article-category")
    public ResponseObject findAllArticleByCategory(final UserFindArticleByCategoryRequest request) {
        return new ResponseObject(userArticleService.findAllArticleByCategory(userId,request));
    }

    @GetMapping("/by-browse-date")
    public ResponseObject findAllArticleByBrowseDate(final UserFindArticleRequest request) {
        return new ResponseObject(userArticleService.findAllArticleByBrowseDate(userId,request));
    }

    @GetMapping("/by-tym")
    public ResponseObject findAllArticleByTym(final UserFindArticleRequest request) {
        return new ResponseObject(userArticleService.findAllArticleByTym(userId,request));
    }

    @GetMapping("/author")
    public ResponseObject findAllArticleByAuthorId(final UserFindArticleAuthorRequest request) {
        return new ResponseObject(userArticleService.findArticleByIdAuthorId(userId,request));
    }

    @GetMapping("/{id}")
    public ResponseObject getArticleById(@PathVariable("id") String id) {
        return new ResponseObject(userArticleService.getArticleById(userId, id));
    }
}
