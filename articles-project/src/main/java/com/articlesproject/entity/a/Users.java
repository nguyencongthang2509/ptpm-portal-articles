package com.articlesproject.entity.a;

import com.articlesproject.entity.base.PrimaryEntity;
import com.articlesproject.infrastructure.constant.EntityProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@ToString
public class Users extends PrimaryEntity {
    @Column(length = EntityProperties.LENGTH_CODE, nullable = false)
    private String ma;

    @Column(length = EntityProperties.LENGTH_NAME_SHORT, nullable = false)
    private String ten;

    @Column(length = EntityProperties.LENGTH_PHONE, nullable = false)
    private String soDienThoai;

    @Column(length = EntityProperties.LENGTH_EMAIL, nullable = false)
    private String email;

    @Column(length = EntityProperties.LENGTH_ID, nullable = false)
    private Integer role;
}
