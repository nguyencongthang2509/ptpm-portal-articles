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
@Table(name = "danh_gia")
public class DanhGia extends PrimaryEntity {

    @Column(nullable = false)
    private Integer sao;

    @Column(nullable = false)
    private Integer tym;

    @Column(nullable = false)
    private Long thoiGianDanhGia;

    @Column(nullable = false)
    private Long thoiGianTym;

}
