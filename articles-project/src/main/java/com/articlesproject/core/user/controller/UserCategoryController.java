package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserCategoryController extends BaseController {
    @Autowired
    private UserCategoryService userCategoryService;

    @GetMapping("")
    public ResponseObject getAllCategory(){
        return new ResponseObject(userCategoryService.getAllCategory());
    }
}
