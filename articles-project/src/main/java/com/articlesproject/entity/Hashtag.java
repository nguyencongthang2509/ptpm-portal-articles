package com.articlesproject.entity;

import com.articlesproject.entity.base.PrimaryEntity;
import com.articlesproject.infrastructure.constant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;

@Entity
@Data
@ToString
@Table(name = "hashtag")
public class Hashtag extends PrimaryEntity {
    @Nationalized
    @Column(length = EntityProperties.LENGTH_NoiDung)
    private String title;
}
