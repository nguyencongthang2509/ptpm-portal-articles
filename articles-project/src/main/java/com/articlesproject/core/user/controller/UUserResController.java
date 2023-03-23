package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UUserResController {

    @Value("${app.UserId}") private String id;

    @Autowired
    private UUserService uUserService;

    @GetMapping("/detail/{id}")
    private ResponseObject findByUserId(@PathVariable("id") String id) {
        return new ResponseObject(uUserService.findByIdUser(id));
    }

    @GetMapping("/my-user-detail")
    private ResponseObject findByUserId() {
        String userId = this.id;
        return new ResponseObject(uUserService.findByIdUser(userId));
    }
}
