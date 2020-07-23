package com.keer.bdql.Domain.mongo;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * mongoDB 中交易数据集中的asset 对象
 */
public class Asset implements Serializable {
    @Field(value = "id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
