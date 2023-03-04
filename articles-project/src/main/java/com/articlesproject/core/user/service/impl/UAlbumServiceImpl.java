package com.articlesproject.core.user.service.impl;

import com.articlesproject.core.user.model.request.CreateAlbumRequest;
import com.articlesproject.core.user.model.request.UpdateAlbumRequest;
import com.articlesproject.core.user.model.respone.AlbumRespone;
import com.articlesproject.core.user.repository.UAlbumRepository;
import com.articlesproject.core.user.repository.UUserRepository;
import com.articlesproject.core.user.service.UAlbumService;
import com.articlesproject.entity.Album;
import com.articlesproject.infrastructure.constant.Message;
import com.articlesproject.infrastructure.exception.rest.RestApiException;
import com.articlesproject.util.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UAlbumServiceImpl implements UAlbumService {

    @Autowired
    private UAlbumRepository albumRepository;

    private FormUtils formUtils = new FormUtils();


    @Override
    public List<AlbumRespone> findAllAlbumByUserId(String userId) {
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
}
