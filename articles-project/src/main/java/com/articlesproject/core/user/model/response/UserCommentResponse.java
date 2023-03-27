package com.articlesproject.core.user.model.response;

import com.articlesproject.entity.Comments;
import com.articlesproject.entity.Users;
import com.articlesproject.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Comments.class, Users.class})
public interface UserCommentResponse extends IsIdentified {

    @Value("#{target.content}")
    String getContent();

    @Value("#{target.reply}")
    String getReply();

    @Value("#{target.userID}")
    String getUserID();

    @Value("#{target.userName}")
    String getUserName();

    @Value("#{target.userImg}")
    String getUserImg();

    @Value("#{target.created_date}")
    String getCreateDate();
}
