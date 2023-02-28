package com.articlesproject.entity.a;


import com.articlesproject.entity.base.PrimaryEntity;
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

    @Column(nullable = false)
    private Integer tym;

    @Column(nullable = false)
    private Long thoiGianDanhGia;

    @Column(nullable = false)
    private Long thoiGianTym;

}
