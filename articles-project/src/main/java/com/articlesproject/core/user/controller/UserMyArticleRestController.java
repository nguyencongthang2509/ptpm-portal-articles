package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.core.user.service.UserArticleHashtagService;
import com.articlesproject.core.user.service.UserMyArticleService;
import com.articlesproject.entity.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RestController
@RequestMapping("/api/my-article")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserMyArticleRestController extends BaseController {

    @Value("${app.UserId}")
    private String id;

    @Autowired
    private UserMyArticleService userMyArticleService;

    @Autowired
    private UserArticleHashtagService articleHashtagService;

    @GetMapping("")
    public ResponseEntity<PageableObject<UserMyArticleResponse>> getAllMyArticle(final UserMyArticleRequest request) {
        String idUser = id;

        PageableObject<UserMyArticleResponse> listMyArticle = userMyArticleService.getAllMyArticle(request, idUser);
        return ResponseEntity.ok(listMyArticle);
    }

    @PostMapping("/create-article")
    public ResponseObject createArticle(@RequestBody UserCreateArticleRequest request) throws IOException {
        request.setUsersId(id);
        Articles articles = userMyArticleService.addArticle(request);
        articleHashtagService.addTagsArticle(request.getHashtag(), articles.getId());
        return new ResponseObject("Add article successfully!");
    }

        @PutMapping("/update-article/{id}")
    public ResponseObject updateArticle(@PathVariable("id") String id, @RequestBody UserUpdateArticleRequest request) throws IOException {
        Articles articles = userMyArticleService.updateArticle(id, request);
        articleHashtagService.updateTagsArticle(request.getHashtag(), articles.getId());
        return new ResponseObject("Update article successfully!");
    }

    @GetMapping("/detail-update-my-article/{id}")
    public ResponseObject detailUpdateMyArticle(@PathVariable("id") String id) {
        String userId = this.id;
        return new ResponseObject(userMyArticleService.getArticleById(id, userId));
    }

    @GetMapping("/detail-my-article/{id}")
    public ResponseObject detailMyArticle(@PathVariable("id") String id) {
        String userId = this.id;
        return new ResponseObject(userMyArticleService.getArticleById(id, userId));
    }

    @DeleteMapping("/delete-article/{id}")
    public ResponseObject deleteArticle(@PathVariable("id") String id) {
        return new ResponseObject(userMyArticleService.deleteArticle(id));
    }
}
