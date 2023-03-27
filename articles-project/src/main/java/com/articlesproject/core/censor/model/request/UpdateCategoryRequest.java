package com.articlesproject.core.censor.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequest {

    private String id;

    private String code;

    private String name;
}
