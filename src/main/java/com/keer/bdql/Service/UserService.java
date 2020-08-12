package com.keer.bdql.Service;

import com.keer.bdql.pojo.WebResult;

public interface UserService {

    /**
     * 用户注册
     * @param userName
     * @param password
     * @return
     */
    WebResult register(String userName,String password);

    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    WebResult login(String userName,String password);
}
