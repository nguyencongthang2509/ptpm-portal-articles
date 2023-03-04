package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.BaseController;
import com.articlesproject.core.user.model.request.CreateAlbumRequest;
import com.articlesproject.core.user.model.request.CreateArticleAlbumRequest;
import com.articlesproject.core.user.model.request.UpdateAlbumRequest;
import com.articlesproject.core.user.service.UAlbumService;
import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.service.UArticleAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/album")
public class UAlbumResController extends BaseController {

    @Autowired
    private UAlbumService albumService;

    @Autowired
    private UArticleAlbumService articleAlbumService;

    @PostMapping("/create")
    private ResponseObject create(@RequestBody CreateAlbumRequest request){
        request.setUsersId(session.getUserId());
        return new ResponseObject(albumService.create(request));
    }

    @PostMapping("/create-album")
    private ResponseObject createArticleToAlbum(@RequestBody CreateArticleAlbumRequest request){
        return new ResponseObject(articleAlbumService.create(request));
    }

    @PutMapping("/edit")
    private ResponseObject update(@RequestBody UpdateAlbumRequest request){
        return new ResponseObject(albumService.update(request));
    }

    @DeleteMapping
    private ResponseObject deleteArticleInAlbum(@RequestParam("id") String id){
        return new ResponseObject(articleAlbumService.delete(id));
    }

    @GetMapping
    private ResponseObject findAllAlbumByUserId(){
        String userId = session.getUserId();
        return new ResponseObject(albumService.findAllAlbumByUserId(userId));
    }
}
