package com.articlesproject.core.user.model.response;

import com.articlesproject.entity.Album;
import com.articlesproject.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Album.class})
public interface AlbumResponse extends IsIdentified {

    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.numberOfArticle}")
    int getNumberOfArticle();

    @Value("#{target.created_date}")
    long getCreatAt();
}
