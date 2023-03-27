package com.articlesproject.core.censor.service;

import com.articlesproject.core.censor.model.request.CreateCategoryRequest;
import com.articlesproject.core.censor.model.request.UpdateCategoryRequest;
import com.articlesproject.entity.Category;
import java.util.List;

public interface CensorCategoryService {

    List<Category> getAll();

    Category create(CreateCategoryRequest request);

    Category update(UpdateCategoryRequest request);

    boolean delete(String id);

}
