package com.articlesproject.core.reviewer.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEvaluateRequest {

    private String articlesId;

    private String content;

    private Integer star;

}
