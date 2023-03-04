package com.articlesproject.entity;

import com.articlesproject.entity.base.PrimaryEntity;
import com.articlesproject.infrastructure.constant.EntityProperties;
import lombok.Data;
import lombok.ToString;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Data
@ToString
public class Users extends PrimaryEntity {
    @Column(length = EntityProperties.LENGTH_CODE, nullable = false)
    private String code;

    @Column(length = EntityProperties.LENGTH_NAME_SHORT, nullable = false)
    private String name;

    @Column(length = EntityProperties.LENGTH_PHONE, nullable = false)
    private String phoneNumber;

    @Column(length = EntityProperties.LENGTH_EMAIL, nullable = false)
    private String email;

    @Column(length = EntityProperties.LENGTH_DESCRIPTION, nullable = false)
    private String img;

    @Column(length = EntityProperties.LENGTH_ID, nullable = false)
    private Integer role;
}
