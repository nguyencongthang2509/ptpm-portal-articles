package com.articlesproject.core.user.model.respone;

import com.articlesproject.entity.Album;
import com.articlesproject.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Album.class})
public interface AlbumRespone extends IsIdentified {

    @Value("#{target.title}")
    String getTitle();

    @Value("#{target.soLuong}")
    int getSoLuong();

    @Value("#{target.created_date}")
    long getNgayTao();
}
