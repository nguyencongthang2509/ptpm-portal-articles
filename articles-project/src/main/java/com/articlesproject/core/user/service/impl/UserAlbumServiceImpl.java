package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.request.CreateAlbumRequest;
import com.articlesproject.core.user.model.request.UpdateAlbumRequest;
import com.articlesproject.core.user.model.response.AlbumResponse;
import com.articlesproject.core.user.repository.UserAlbumRepository;
import com.articlesproject.core.user.repository.UserArticleAlbumRepository;
import com.articlesproject.core.user.service.UserAlbumService;
import com.articlesproject.entity.Album;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UserAlbumServiceImpl implements UserAlbumService {

    @Autowired
    private UserAlbumRepository albumRepository;

    @Autowired
    private UserArticleAlbumRepository articleAlbumRepository;


    private FormUtils formUtils = new FormUtils();


    @Override
    public List<AlbumResponse> findAllAlbumByUserId(String userId) {
        return albumRepository.findAllAlbumByUserId(userId);
    }

    @Override
    public Album create(CreateAlbumRequest request) {
        Album album =  formUtils.convertToObject(Album.class, request);
        return albumRepository.save(album);
    }

    @Override
    public Album update(UpdateAlbumRequest request) {
        Optional<Album> album = albumRepository.findById(request.getId());
        if(!album.isPresent()){
            throw new RestApiException(Message.ALBUM_NOT_EXIST);
        }
        album.get().setTitle(request.getTitle());
        return albumRepository.save(album.get());
    }

    @Override
    public boolean delete(String id) {
        Optional<Album> album = albumRepository.findById(id);
        if(!album.isPresent()){
            throw new RestApiException(Message.ALBUM_NOT_EXIST);
        }
        articleAlbumRepository.deleteByAlbumId(id);
        albumRepository.deleteById(id);
        return true;
    }

    @Override
    public Album findById(String id) {
        Optional<Album> album = albumRepository.findById(id);
        if(!album.isPresent()){
            throw new RestApiException(Message.ALBUM_NOT_EXIST);
        }
        return album.get();
    }
}
