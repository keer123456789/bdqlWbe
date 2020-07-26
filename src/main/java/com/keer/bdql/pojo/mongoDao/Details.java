package com.keer.bdql.pojo.mongoDao;

import java.io.Serializable;
/**
 * Transaction数据集中的outputs对象中condition对象中的Details对象
 */
public class Details implements Serializable {
    private String public_key;
    private String type;

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
