package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.UserArticleRequest;
import com.articlesproject.core.user.model.request.UserCreateArticleRequest;
import com.articlesproject.core.user.model.request.UserFindArticleRequest;
import com.articlesproject.core.user.model.request.UserUpdateArticleRequest;
import com.articlesproject.core.user.service.UserArticleHashtagService;
import com.articlesproject.core.user.service.UserArticleService;
import com.articlesproject.entity.Articles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/article")
@CrossOrigin(origins = {"*"}, maxAge = 4800, allowCredentials = "false")
public class UserArticleRestController extends BaseController {

    @Value("${app.UserId}")
    private String id;

    @Autowired
    private UserArticleService userArticleService;
    @Autowired
    private UserArticleHashtagService articleHashtagService;

    @GetMapping("")
    public ResponseObject getAllArticle(final UserArticleRequest request) {
        return new ResponseObject(userArticleService.getAllArticle(request));
    }

    @GetMapping("/search")
    public ResponseObject findAllArticle(@RequestBody UserFindArticleRequest request) {
        return new ResponseObject(userArticleService.FindAllArticle(request));
    }

    @PostMapping("/create-article")
    public ResponseEntity<String> createArticle(@RequestBody UserCreateArticleRequest request) throws IOException {
        request.setUsersId("97f152fe-4250-400a-8605-743a3a3e66f8");
        String currentDirectory1 = System.getProperty("user.dir");
        Articles articles = userArticleService.addArticle(request);
        articleHashtagService.addTagsArticle(request.getHashtag(), articles.getId());
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

    @DeleteMapping("/delete-article/{id}")
    public ResponseObject deleteArticle(@PathVariable("id") String id) {
        return new ResponseObject(userArticleService.deleteArticle(id));
    }

    @GetMapping("/{id}")
    public ResponseObject getArticleById(@PathVariable("id") String id) {
        return new ResponseObject(userArticleService.getArticleById(id));
    }
}
