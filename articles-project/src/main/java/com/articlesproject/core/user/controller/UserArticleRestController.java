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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserArticleRestController extends BaseController {

//    @Value("${app.UserId}")
//    private String id;

    @PostMapping("/create-article")
    public ResponseEntity<String> createArticle(@RequestBody UserCreateArticleRequest request) throws IOException {
        request.setUsersId("97f152fe-4250-400a-8605-743a3a3e66f8");
        String currentDirectory1 = System.getProperty("user.dir");
        Articles articles = userArticleService.addArticle(request);
        String folderName = articles.getId();
        String folderPath = currentDirectory1 + "/articles-project/src/main/resources/templates/articles/" + folderName;
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
            String fileName = "toi-thanh-cong-roi.html";

            File dir = new File(folderPath);
            File actualFile = new File(dir, fileName);
            actualFile.getParentFile().mkdirs();
            actualFile.createNewFile();
            FileWriter fileWriter = new FileWriter(actualFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(request.getContent());

            bufferedWriter.close();
            System.out.println("File saved successfully!");
            return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("File upload failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Autowired
    private UserArticleService userArticleService;

    @GetMapping("")
    public ResponseEntity<PageableObject<UserArticleResponse>> getAllArticle(final UserArticleRequest request) {
        PageableObject<UserArticleResponse> listArticle = userArticleService.getAllArticle(request);
        return ResponseEntity.ok(listArticle);
    }

    @PutMapping("/update-article/{id}")
    public ResponseObject updateArticle(@PathVariable("id") String id, @RequestBody UserUpdateArticleRequest request) {
        return new ResponseObject(userArticleService.updateArticle(id, request));
    }

    @DeleteMapping("/delete-article/{id}")
    public ResponseObject deleteArticle(@PathVariable("id") String id) {
        return new ResponseObject(userArticleService.deleteArticle(id));
    }


}
