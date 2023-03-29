package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.user.model.request.UserCreateAlbumRequest;
import com.articlesproject.core.user.model.request.UserCreateArticleAlbumRequest;
import com.articlesproject.core.user.model.request.UserFindArticleByAlbumRequest;
import com.articlesproject.core.user.model.request.UserUpdateAlbumRequest;
import com.articlesproject.core.user.service.UserAlbumService;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.service.UserArticleAlbumService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/album")
public class UserAlbumResController extends BaseController {

    @Value("${app.UserId}") private String id;

    @Autowired
    private UserAlbumService albumService;

    @Autowired
    private UserArticleAlbumService articleAlbumService;

    @GetMapping
    private ResponseObject findAllAlbumByUserId(){
        String userId = id;
        return new ResponseObject(albumService.findAllAlbumByUserId(userId));
    }

    @PostMapping("/create")
    private ResponseObject createAlbum(@Valid @RequestBody UserCreateAlbumRequest request){
        String userId = id;
        return new ResponseObject(albumService.create(request, userId));
    }

    @PutMapping("/update")
    private ResponseObject updateAlbum(@Valid @RequestBody UserUpdateAlbumRequest request){
        return new ResponseObject(albumService.update(request));
    }

    @GetMapping("/detail-album-user/{articleId}")
    private ResponseObject findAllSimpleAllBumByUserId(@PathVariable("articleId") String articleId){
        String userId = id;
        return new ResponseObject(albumService.findAllSimpleAllBumByUserId(userId, articleId));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseObject deleteAlbum(@PathVariable("id") String id){
        return new ResponseObject(albumService.delete(id));
    }

    @GetMapping("/{userId}")
    private ResponseObject findAllAlbumPublicByUserId(@PathVariable("userId") String userId){
        return new ResponseObject(albumService.findAllAlbumPublicByUserId(userId));
    }

    @GetMapping("/find-album-user/{id}")
    private ResponseObject findByIdAndUsersId(@PathVariable("id") String id ){
        String userId = this.id;
        return new ResponseObject(albumService.findByIdAndUsersId(id, userId));
    }

    @GetMapping("/detail/{id}")
    private ResponseObject detailAlbum(@PathVariable("id") String id){
        String userId = this.id;
        return new ResponseObject(albumService.findByIdAndUsersId(id, userId));
    }

    @PostMapping("/add-article")
    private ResponseObject addArticleToAlbum(@Valid @RequestBody UserCreateArticleAlbumRequest request){
        return new ResponseObject(articleAlbumService.createArticleAlbum(request));
    }

    @DeleteMapping("/delete-all-article")
    private ResponseObject deleteArticlesInAlbum(@RequestParam("articleId") String articleId, @RequestParam("albumId") String albumId){
        return new ResponseObject(articleAlbumService.deleteArticleAlbum(articleId,albumId));
    }

    @GetMapping("/detail-article-by-album")
    private ResponseObject detailArticleByAlbum(final UserFindArticleByAlbumRequest request){
        String userId = this.id;
        return new ResponseObject(articleAlbumService.findAllArticleByAlbum(userId, request));
    }

}

