package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.core.user.service.UserMyArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myArticle")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserMyArticleRestController extends BaseController {
    @Autowired
    private UserMyArticleService userMyArticleService;

    @GetMapping("")
    public ResponseEntity<PageableObject<UserMyArticleResponse>> getAllMyArticle(final UserMyArticleRequest request) {
//        String idUser = session.getUserId();
        String idUser = "0e8e9541-3df4-4695-a754-6c5f864d8a92";

        PageableObject<UserMyArticleResponse> listMyArticle = userMyArticleService.getAllMyArticle(request, idUser);
        return ResponseEntity.ok(listMyArticle);
    }

}
