package com.keer.bdql.pojo.mongoDao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * mongodb中 Asset数据集的对象
 */
@Document(collection = "assets")
public class Assets implements Serializable {
    @Id
    private String _id;

    @Field(value = "data")
    private TableData data;

    @Field(value = "id")
    private String id;

    public Assets() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public TableData getData() {
        return data;
    }

    public void setData(TableData data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
