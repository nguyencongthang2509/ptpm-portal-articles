package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserArticleRestController extends BaseController {

    @Autowired
    private UserArticleService userArticleService;

    @GetMapping("")
    public List<UserArticleResponse> getAllArticle(){
        return userArticleService.getAllArticle();
    }

}
