package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.request.CreateAlbumRequest;
import com.articlesproject.core.user.model.request.UpdateAlbumRequest;
import com.articlesproject.core.user.model.respone.AlbumRespone;
import com.articlesproject.entity.Album;

import java.util.List;


public interface UAlbumService {

    List<AlbumRespone> findAllAlbumByUserId(String userId);

    Album create(CreateAlbumRequest request);

    Album update(UpdateAlbumRequest request);
}
