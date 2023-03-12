package com.articlesproject.core.user.service;

import com.articlesproject.core.user.model.request.CreateAlbumRequest;
import com.articlesproject.core.user.model.request.UpdateAlbumRequest;
import com.articlesproject.core.user.model.response.AlbumResponse;
import com.articlesproject.core.user.model.response.UserArticleAlbumResponse;
import com.articlesproject.entity.Album;

import java.util.List;


public interface UserAlbumService {

    List<AlbumResponse> findAllAlbumByUserId(String userId);

    List<AlbumResponse> findAllAlbumPublicByUserId(String userId);

    List<UserArticleAlbumResponse> getAllArticleFavorite( String userId);

    Album create(CreateAlbumRequest request, String userId);

    Album update(UpdateAlbumRequest request);

    boolean delete(String id);

    Album findById(String id);
}
