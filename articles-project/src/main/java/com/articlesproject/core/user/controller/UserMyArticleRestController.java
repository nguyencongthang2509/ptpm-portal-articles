package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.PageableObject;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.UserMyArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.model.response.UserMyArticleResponse;
import com.articlesproject.core.user.service.UserArticleHashtagService;
import com.articlesproject.core.user.service.UserMyArticleService;
import com.articlesproject.entity.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @PutMapping("/update-article/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable("id") String id, @RequestBody UserUpdateArticleRequest request) throws IOException {
        String currentDirectory1 = System.getProperty("user.dir");
        Articles articles = userMyArticleService.updateArticle(id, request);
        articleHashtagService.updateTagsArticle(request.getHashtag(), articles.getId());
        String folderName = articles.getId();
        String folderPath = currentDirectory1 + "/articles-project/src/main/resources/templates/articles/" + folderName;
        File folder = new File(folderPath);
        File imageFile = new File(folderPath + "/image.jpeg");
        if (folder.exists()) {
            imageFile.delete();
            String fileName = "toi-thanh-cong-roi.html";
            File dir = new File(folderPath);
            File actualFile = new File(dir, fileName);
            FileWriter fileWriter = new FileWriter(actualFile);
            fileWriter.write(request.getContent());
            fileWriter.close();
            String regex = "data:image/(png|jpeg|jpg);base64,([^\"]+)";
            Pattern pattern = Pattern.compile(regex);
            String html = new String(Files.readAllBytes(Paths.get(folderPath + "/toi-thanh-cong-roi.html")));
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                String extension = matcher.group(1);
                String base64Data = matcher.group(2);
                byte[] imageData = Base64.getDecoder().decode(base64Data);
                String imageName = "image" + "." + extension;
                Files.write(Paths.get(folderPath + "/" + imageName), imageData, StandardOpenOption.CREATE_NEW);
                break;
            }
            return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("File upload failed!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/detail-update-my-article/{id}")
    public ResponseObject detailUpdateMyArticle(@PathVariable("id") String id) {
        return new ResponseObject(userMyArticleService.getArticleById(id));
    }

    @GetMapping("/detail-my-article/{id}")
    public ResponseObject detailMyArticle(@PathVariable("id") String id) {
        return new ResponseObject(userMyArticleService.getArticleById(id));
    }
}
