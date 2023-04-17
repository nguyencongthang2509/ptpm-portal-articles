package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserHistoryRestController extends BaseController {
    @Value("${app.UserId}")
    private String userId;

    @Autowired
    private UserHistoryService userHistoryService;

    @GetMapping("")
    public ResponseObject getAllHistory(){
        return new ResponseObject(userHistoryService.findAllHistory(userId));
    }
}
