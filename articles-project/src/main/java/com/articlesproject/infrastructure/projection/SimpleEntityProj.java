package com.articlesproject.infrastructure.projection;

import com.articlesproject.entity.base.IsIdentified;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {})
public interface SimpleEntityProj extends IsIdentified {
    String getName();
}
