package com.keer.bdql.Service.Implement;

import com.keer.bdql.Service.UserService;
import com.keer.bdql.Util.KeyPairUtil;
import com.keer.bdql.dao.UserDao;
import com.keer.bdql.pojo.WebResult;
import com.keer.bdql.pojo.mongoDao.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserDao userDao;

    @Override
    public WebResult register(String userName, String password) {
        WebResult webResult = new WebResult();
        if (!userDao.checkUserNameExit(userName)) {
            KeyPair keyPair = KeyPairUtil.genKeyPair();
            String key = KeyPairUtil.getPriKey(keyPair);
            User user = new User();
            user.setPassword(password);
            user.setUserName(userName);
            user.setPubKey(KeyPairUtil.getPubKey(keyPair));
            if (userDao.save(user)) {
                webResult.setMessage(WebResult.MSG_SUCCESS);
                webResult.setCode(WebResult.CODE_SUCCESS);
                webResult.setData(key);
                logger.info("用户信息注册成功，密钥："+key);
            } else {
                webResult.setCode(WebResult.CODE_ERROR_PARAMERROR);
                webResult.setMessage(WebResult.MSG_ERROR_PARAMERROR);
                logger.error("用户信息存入mongodb错误，用户信息："+user.toString());
            }
        } else {
            webResult.setCode(WebResult.CODE_ERROR_PARAMERROR);
            webResult.setMessage(WebResult.MSG_ERROR_PARAMERROR);
            logger.error("用户名重复，请检查，用户名："+userName);
        }
        return webResult;
    }

    @Override
    public WebResult login(String userName, String password) {
        WebResult webResult=new WebResult();
        User user=userDao.findByUserName(userName);
        if(!user.getPassword().equals(password)){
            webResult.setMessage(WebResult.MSG_ERROR);
            webResult.setCode(WebResult.ERROR);
            logger.info("登录失败！用户名密码错误！");
        }else{
            webResult.setMessage(WebResult.MSG_SUCCESS);
            webResult.setCode(WebResult.CODE_SUCCESS);
            logger.info("登录成功！");
        }
        return webResult;
    }
}
