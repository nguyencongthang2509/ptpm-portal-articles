package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.CreateHashtagRequest;
import com.articlesproject.core.user.service.UserArticleHashtagService;
import com.articlesproject.core.user.service.UserHashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/hashtag")
public class UserHashtagResController {

    @Autowired
    private UserHashtagService hashtagService;

    @Autowired
    private UserArticleHashtagService articleHashtagService;

    @GetMapping
    private ResponseObject getAll(){
        return new ResponseObject(hashtagService.getAll());
    }

    @PostMapping("/create")
    private ResponseObject createHahtag(@RequestBody CreateHashtagRequest request){
        return new ResponseObject(hashtagService.createHashtag(request));
    }

}
