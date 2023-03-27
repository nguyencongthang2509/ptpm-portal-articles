package com.articlesproject.core.reviewer.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEvaluateRequest {

    private String articlesId;

    private String content;

    private Integer star;

}
