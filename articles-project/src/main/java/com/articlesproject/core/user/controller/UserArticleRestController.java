package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserArticleRestController extends BaseController {
    @Autowired
    private UserArticleService userArticleService;

    @GetMapping("")
    public ResponseEntity<PageableObject<UserArticleResponse>> getAllArticle(final UserArticleRequest request) {
        PageableObject<UserArticleResponse> listArticle = userArticleService.getAllArticle(request);
        return ResponseEntity.ok(listArticle);
    }

    @PostMapping("/create-article")
    public ResponseObject creaetArticle(@RequestBody UserCreateArticleRequest request){
        request.setUsersId("2404bf9f-987d-4e7d-9906-21c44ee4b72f");
        return new ResponseObject(userArticleService.addArticle(request));
    }

}
