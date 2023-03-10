package com.articlesproject.core.reviewer.model.response;

import com.articlesproject.entity.Evaluate;
import com.articlesproject.entity.base.IsIdentified;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Evaluate.class})
public interface EvaluateRespone extends IsIdentified {

    @Value("#{target.content}")
    String getContent();

    @Value("#{target.created_date}")
    long getCrateDate();

    @Value("#{target.star}")
    int getStar();

    @Value("#{target.userName}")
    String getUserName();

    @Value("#{target.userImg}")
    String getUserImg();

}
