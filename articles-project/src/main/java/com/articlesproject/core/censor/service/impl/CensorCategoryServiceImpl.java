package com.articlesproject.core.censor.service.impl;

import com.articlesproject.core.censor.model.request.CreateCategoryRequest;
import com.articlesproject.core.censor.model.request.UpdateCategoryRequest;
import com.articlesproject.core.censor.repository.CensorArticleRepository;
import com.articlesproject.core.censor.repository.CensorCategoryRepository;
import com.articlesproject.core.censor.service.CensorCategoryService;
import com.articlesproject.entity.Articles;
import com.articlesproject.entity.Category;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class CensorCategoryServiceImpl implements CensorCategoryService {

    @Autowired
    private CensorCategoryRepository categoryRepository;

    @Autowired
    private CensorArticleRepository articleRepository;

    private FormUtils formUtils = new FormUtils();


    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(CreateCategoryRequest request) {
        Category category = categoryRepository.findByCode(request.getCode());
        if(category !=null){
            throw new RestApiException(Message.CATEGORY_CODE_ALREADY_EXIST);
        }
        Category newCategory = formUtils.convertToObject(Category.class, request);
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category update(UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(request.getId()).orElse(null);
        if(category == null){
            throw new RestApiException(Message.CATEGORY_NOT_EXIST);
        }
        category.setCode(request.getCode());
        category.setName(request.getName());
        return categoryRepository.save(category);
    }

    @Override
    public boolean delete(String id) {
        Category category = categoryRepository.findById(id).orElse(null);
        List<Articles> articles = articleRepository.findByCategoryId(id);
        if(category == null){
            throw new RestApiException(Message.CATEGORY_NOT_EXIST);
        }if(articles.size() != 0){
            throw new RestApiException(Message.ARTICLE_ALREADY_EXIST);
        }
        categoryRepository.deleteById(id);
        return true;
    }
}
