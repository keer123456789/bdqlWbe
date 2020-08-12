package com.keer.bdql.pojo.mongoDao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * mongodb中用户信息
 */
@Document(collection = "user")
public class User {
    @Id
    private String _id;
    @Field(value = "userName")
    private String userName;
    @Field(value = "password")
    private String password;
    @Field(value = "pubKey")
    private String pubKey;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", pubKey='" + pubKey + '\'' +
                '}';
    }
}
