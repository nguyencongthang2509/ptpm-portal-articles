package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserArticleRestController extends BaseController {

    @Value("${app.UserId}") private String id;

    @Autowired
    private UserArticleService userArticleService;

    @GetMapping("")
    public ResponseEntity<PageableObject<UserArticleResponse>> getAllArticle(final UserArticleRequest request) {
        PageableObject<UserArticleResponse> listArticle = userArticleService.getAllArticle(request);
        return ResponseEntity.ok(listArticle);
    }

    @PostMapping("/create-article")
    public ResponseObject creaetArticle(@RequestBody UserCreateArticleRequest request){
        request.setUsersId(id);
        return new ResponseObject(userArticleService.addArticle(request));
    }

    @PutMapping("update-article/{fileName}")
    public ResponseObject updateArticle(@PathVariable("fileName") String fileName, @RequestBody UserUpdateArticleRequest request){
        return new ResponseObject(userArticleService.updateArticle(fileName, request));
    }

    @DeleteMapping("delete-article/{id}")
    public ResponseObject deleteArticle(@PathVariable("id") String id){
        return new ResponseObject(userArticleService.deleteArticle(id));
    }
    @PostMapping("/download")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String currentDirectory = System.getProperty("user.dir");
            String filePath = currentDirectory + "/articles-project/src/main/resources/templates/articles/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("File upload failed!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
