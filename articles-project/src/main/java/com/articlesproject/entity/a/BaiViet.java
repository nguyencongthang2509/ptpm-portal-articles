package com.articlesproject.entity.a;

import com.articlesproject.entity.base.PrimaryEntity;
import com.articlesproject.infrastructure.constant.EntityProperties;
import jakarta.persistence.Lob;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Data
@ToString
@Table(name = "bai_viet")
public class BaiViet extends PrimaryEntity {

    @Nationalized
    @Column(length = EntityProperties.LENGTH_NoiDung, nullable = false)
    private String tieuDe;

    @Column( nullable = false, length = EntityProperties.LENGTH_DESCRIPTION )
    private String noiDung;

    @Column(nullable = false)
    private Integer trangThai;

    @Column(nullable = false)
    private Long createDate;

    @Column(nullable = false)
    private Integer tym;

    @Column(nullable = false)
    private Long thoiGianKiemDuyet;

    @Column(length = EntityProperties.LENGTH_ID)
    private String usersId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String theLoaiId;

}

