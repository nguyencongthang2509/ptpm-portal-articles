package com.articlesproject.entity;

import com.articlesproject.entity.base.PrimaryEntity;
import com.articlesproject.infrastructure.constant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "articles_album")
public class ArticlesAlbum extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    private String articlesId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String albumId;
}
