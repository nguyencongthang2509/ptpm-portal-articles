package com.articlesproject.core.user.controller;

import com.articlesproject.core.common.base.ResponseObject;
import com.articlesproject.core.user.model.request.CreateCommentRequest;
import com.articlesproject.core.user.model.request.UpdateCommentRequest;
import com.articlesproject.core.user.service.UCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/comment")
public class UCommentResController {

    @Autowired
    private UCommentService commentService;

    @GetMapping("/detail-comment-article/{articleId}")
    private ResponseObject findCommentByArticleId(@PathVariable("articleId") String articleId){
        return  new ResponseObject(commentService.findCommentByArticleId(articleId));
    }

    @PostMapping("/create")
    private ResponseObject createComment(@RequestBody CreateCommentRequest request){
        return new ResponseObject(commentService.create(request));
    }

    @PutMapping("/update")
    private ResponseObject updateComment(@RequestBody UpdateCommentRequest request){
        return new ResponseObject(commentService.update(request));
    }

    @DeleteMapping("/delete/{commentId}")
    private ResponseObject deleteComment(@PathVariable("commentId") String commentId){
        return new ResponseObject(commentService.delete(commentId));
    }
}
