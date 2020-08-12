package com.keer.bdql.dao.implement;

import com.keer.bdql.dao.UserDao;
import com.keer.bdql.pojo.mongoDao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 检查用户名是否存在 存在返回true 不存在返回false
     *
     * @param userName
     * @return
     */
    @Override
    public boolean checkUserNameExit(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        List<User> users = mongoTemplate.find(query, User.class);
        if (users.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 写入用户信息
     *
     * @param user
     * @return
     */
    @Override
    public boolean save(User user) {
        if (mongoTemplate.save(user, "user") != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findByUserName(String userName) {
        Query query = new Query(Criteria.where("userName").is(userName));
        List<User> users = mongoTemplate.find(query, User.class);
        return users.get(0);
    }
}
