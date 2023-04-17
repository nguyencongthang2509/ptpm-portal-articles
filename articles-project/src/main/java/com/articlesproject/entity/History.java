package com.articlesproject.entity;

import com.articlesproject.entity.base.PrimaryEntity;
import com.articlesproject.infrastructure.constant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "history")
public class History extends PrimaryEntity {

    @Column(length = EntityProperties.LENGTH_ID)
    private String articlesId;

    @Column(length = EntityProperties.LENGTH_ID)
    private String usersId;

    @Column(nullable = false)
    private Long createAt;
}
