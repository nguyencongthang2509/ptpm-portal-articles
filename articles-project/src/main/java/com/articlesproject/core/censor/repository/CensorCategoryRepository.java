package com.articlesproject.core.censor.repository;

import com.articlesproject.entity.Category;
import com.articlesproject.repository.CategoryRepository;

public interface CensorCategoryRepository extends CategoryRepository {

    Category findByCode(String code);

}
