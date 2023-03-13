package com.articlesproject.core.user.model.response;

import com.articlesproject.entity.Album;
import com.articlesproject.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Album.class})
public interface UserAlbumResponse extends IsIdentified {

    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.status}")
    boolean getStatus();

    @Value("#{target.userName}")
    String getUserName();

    @Value("#{target.userImg}")
    String getUserImg();

    @Value("#{target.numberOfArticle}")
    int getNumberOfArticle();

    @Value("#{target.created_date}")
    long getCreatAt();
}
