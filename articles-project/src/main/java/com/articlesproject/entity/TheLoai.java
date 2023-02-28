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
@Table(name = "the_loai")
public class TheLoai extends PrimaryEntity{

    @Column(length = EntityProperties.LENGTH_CODE, nullable = false)
    private String ma;

    @Column(length = EntityProperties.LENGTH_NAME_SHORT)
    @Nationalized
    private String ten;
}
