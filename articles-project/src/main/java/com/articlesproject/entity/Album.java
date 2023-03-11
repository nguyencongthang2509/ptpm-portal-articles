package com.articlesproject.entity;

import com.articlesproject.entity.base.PrimaryEntity;
import com.articlesproject.infrastructure.constant.EntityProperties;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Data
@ToString
@Table(name = "album")
public class Album extends PrimaryEntity {

    @Nationalized
    @Column(length = EntityProperties.LENGTH_NoiDung)
    private String title;

    @Column(length = EntityProperties.LENGTH_ID)
    private String usersId;

    @Column(columnDefinition = "BIT default 0")
    private boolean status;

}


