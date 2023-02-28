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
@Table(name = "danh_gia")
public class DanhGia extends PrimaryEntity {

    @Column(nullable = false)
    private Integer sao;

    @Lob
    @Column( nullable = false, length = EntityProperties.LENGTH_DESCRIPTION)
    private String noiDung;

    @Column(nullable = false)
    private Long thoiGianDanhGia;

    @Column(length = EntityProperties.LENGTH_ID)
    private String baiVietId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String usersId;


}
