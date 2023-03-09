package com.articlesproject.core.censor.controller;

import com.articlesproject.core.censor.model.request.CreateCategoryRequest;
import com.articlesproject.core.censor.model.request.UpdateCategoryRequest;
import com.articlesproject.core.censor.service.CensorCategoryService;
import com.articlesproject.core.common.base.ResponseObject;
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
@RequestMapping("/censor/api/category")
public class CensorCategoryResController {

    @Autowired
    private CensorCategoryService categoryService;

    @GetMapping()
    public ResponseObject getAll(){
        return  new ResponseObject(categoryService.getAll());
    }

    @PostMapping("/create")
    public ResponseObject createCategory(@RequestBody CreateCategoryRequest request){
        return  new ResponseObject(categoryService.create(request));
    }


    @PutMapping("/update")
    public ResponseObject updateCategory(@RequestBody UpdateCategoryRequest request){
        return new ResponseObject(categoryService.update(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseObject deleteCategory(@PathVariable("id") String id){
        return new ResponseObject(categoryService.delete(id));
    }
}
