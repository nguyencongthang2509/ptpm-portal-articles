package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.UserCreateTymRequest;
import com.articlesproject.core.user.service.UserTymService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/tym")
public class UserTymResController {

    @Value("${app.UserId}") private String id;

    @Autowired
    private UserTymService tymService;

    @GetMapping("/all-article-favorite")
    private ResponseObject getAllArticleFavorite(){
        String userId = id;
        return new ResponseObject(tymService.getAllArticleFavorite(userId));
    }

    @PostMapping("/favorite-article")
    private ResponseObject favoriteArticle(@Valid @RequestBody UserCreateTymRequest request){
        return new ResponseObject(tymService.favoriteArticle(id, request));
    }

    @DeleteMapping("/unfavorite-article/{articleId}")
    private ResponseObject unfavoriteArticle(@PathVariable("articleId") String articleId){
        return new ResponseObject(tymService.unfavoriteArticle(id, articleId));
    }
}
