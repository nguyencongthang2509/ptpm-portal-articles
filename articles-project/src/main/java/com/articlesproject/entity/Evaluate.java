package com.articlesproject.entity;


import com.articlesproject.entity.base.PrimaryEntity;
import com.articlesproject.infrastructure.constant.EntityProperties;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Data
@ToString
@Table(name = "evaluate")
public class Evaluate extends PrimaryEntity {

    @Column(nullable = false)
    private Integer star;

    @Column( nullable = false, length = EntityProperties.LENGTH_DESCRIPTION)
    private String content;

    @Column(length = EntityProperties.LENGTH_ID)
    private String articlesId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String usersId;


}
