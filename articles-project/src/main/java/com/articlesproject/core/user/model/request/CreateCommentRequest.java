package com.articlesproject.core.user.model.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    private String articlesId;

    private String content;

    private String usersId;

    private String reply;


}
