package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.request.UserArticleTrashRequest;
import com.articlesproject.core.user.model.request.UserFindArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleTrashResponse;
import com.articlesproject.core.user.service.UserArticleService;
import com.articlesproject.core.user.service.UserArticleTrashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/article-trash")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserArticleTrashRestController extends BaseController {

    @Value("${app.UserId}")
    private String id;

    @Autowired
    private UserArticleTrashService userArticleTrashService;

    @GetMapping("")
    public ResponseObject getAllArticleTrash(final UserArticleTrashRequest request) {
        String idUser = id;
        return new ResponseObject(userArticleTrashService.getAllArticleTrash(request, idUser));
    }

}
