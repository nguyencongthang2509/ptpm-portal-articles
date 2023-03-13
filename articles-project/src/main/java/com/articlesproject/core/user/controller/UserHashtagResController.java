package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.service.UserHashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/hashtag")
public class UserHashtagResController {

    @Autowired
    private UserHashtagService hashtagService;

    @GetMapping
    private ResponseObject getAll(){
        return new ResponseObject(hashtagService.getAll());
    }
}
