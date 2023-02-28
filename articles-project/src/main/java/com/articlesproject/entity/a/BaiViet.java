package com.articlesproject.entity.a;

import com.articlesproject.entity.base.PrimaryEntity;
import com.articlesproject.infrastructure.constant.EntityProperties;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@ToString
@Table(name = "bai_viet")
public class BaiViet extends PrimaryEntity {

    @Nationalized
    @Column(length = EntityProperties.LENGTH_DESCRIPTION, nullable = false)
    private String tieuDe;

    @Nationalized
    @Column(length = EntityProperties.LENGTH_DESCRIPTION, nullable = false)
    private String noiDung;

    @Column(nullable = false)
    private Integer trangThai;

    @Column(nullable = false)
    private Long createDate;

    @Column(nullable = false)
    private Long thoiGianKiemDuyet;

    @Column(length = EntityProperties.LENGTH_ID)
    private String usersId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String theLoaiId;

}

