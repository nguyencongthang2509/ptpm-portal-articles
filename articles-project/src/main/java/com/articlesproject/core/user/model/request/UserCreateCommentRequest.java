package com.articlesproject.core.user.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateCommentRequest {

    private String articlesId;

    private String content;

    private String reply;


}
