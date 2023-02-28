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
@Table(name = "diem")
public class Diem extends PrimaryEntity{

    @Nationalized
    @Column(length = EntityProperties.LENGTH_CODE, nullable = false)
    private Integer diem;

    @Column(nullable = false)
    private Long createAt;

    @Nationalized
    @Column(length = EntityProperties.LENGTH_DESCRIPTION, nullable = false)
    private String phanHoi;

    @Column(nullable = false)
    private Long thoiGianNhanDiem;

    @Column(length = EntityProperties.LENGTH_ID)
    private String baiVietId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String usersId;
}
