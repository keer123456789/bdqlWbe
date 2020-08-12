package com.keer.bdql.dao;

import com.keer.bdql.pojo.mongoDao.User;

public interface UserDao {
    /**
     * 检查用户名是否存在 存在返回true 不存在返回false
     *
     * @param userName
     * @return
     */
    boolean checkUserNameExit(String userName);

    /**
     * 写入用户信息
     * @param user
     * @return
     */
    boolean save(User user);

    User findByUserName(String userName);
}
