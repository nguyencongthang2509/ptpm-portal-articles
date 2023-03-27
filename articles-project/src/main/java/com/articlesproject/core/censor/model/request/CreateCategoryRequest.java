package com.articlesproject.core.censor.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest {

    private String code;

    private String name;
}
