package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserArticleResponse;
import com.articlesproject.core.user.service.UserArticleService;
import com.articlesproject.entity.Articles;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Value("${app.UserId}")
//    private String id;

    @Autowired
    private UserArticleService userArticleService;

    @GetMapping("")
    public ResponseEntity<PageableObject<UserArticleResponse>> getAllArticle(final UserArticleRequest request) {
        PageableObject<UserArticleResponse> listArticle = userArticleService.getAllArticle(request);
        return ResponseEntity.ok(listArticle);
    }

    @PostMapping("/create-article")
    public ResponseObject createArticle(@RequestBody UserCreateArticleRequest request) {
        request.setUsersId("f81c5f4b-5fa9-4224-9e2f-f1f06eafbea5");
        Articles articles = userArticleService.addArticle(request);
        String folderName = articles.getId();
        System.out.println(folderName);
        String currentDirectory = System.getProperty("user.dir");
        String folderPath = currentDirectory + "/articles-project/src/main/resources/templates/articles/" + folderName;
        File folder = new File(folderPath);
        String fileName = "newFile.html";
        if (!folder.exists()) {
            folder.mkdirs();
            File file = new File(folderPath + fileName);
            file.getParentFile().mkdirs();
            try {
                if (file.createNewFile()) {
                    System.out.println("File created!");
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
        }

//        System.out.println(file);
//        try {
//            String filePath = folderPath + file.getOriginalFilename();
//            file.transferTo(new File(filePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return new ResponseObject(articles);
    }

    @PutMapping("/update-article/{id}")
    public ResponseObject updateArticle(@PathVariable("id") String id, @RequestBody UserUpdateArticleRequest request) {
        return new ResponseObject(userArticleService.updateArticle(id, request));
    }

    @DeleteMapping("/delete-article/{id}")
    public ResponseObject deleteArticle(@PathVariable("id") String id) {
        return new ResponseObject(userArticleService.deleteArticle(id));
    }

    @PostMapping("/download")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println(file);
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
