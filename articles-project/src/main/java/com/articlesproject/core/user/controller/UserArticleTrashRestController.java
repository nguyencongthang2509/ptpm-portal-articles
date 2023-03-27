package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.UserArticleTrashRequest;
import com.articlesproject.core.user.service.UserArticleTrashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
        String userId = id;
        return new ResponseObject(userArticleTrashService.getAllArticleTrash(request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseObject deleteArticleTrash(@PathVariable("id") String id) {
        return new ResponseObject(userArticleTrashService.deleteArticle(id));
    }

    @PutMapping("/restore/{id}")
    public ResponseObject restoreArticle(@PathVariable("id") String id) {
        return new ResponseObject(userArticleTrashService.restoreArticle(id));
    }
}
