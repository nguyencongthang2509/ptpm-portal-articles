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
@Table(name = "articles")
public class Articles extends PrimaryEntity {

    @Column( nullable = false, length = EntityProperties.LENGTH_NoiDung )
    private String fileName;

    @Column( nullable = false, length = EntityProperties.LENGTH_NoiDung )
    private String title;

    @Column( nullable = false, length = EntityProperties.LENGTH_NoiDung )
    private String content;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Long createDate;

    @Column(nullable = false)
    private Long lastModifiedDate;

    @Column(nullable = false)
    private Integer tym;

    @Column(nullable = false)
    private Long browseDate;

    @Column(length = EntityProperties.LENGTH_ID)
    private String usersId;

}

