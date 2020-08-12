package com.keer.bdql.Controller;

import com.keer.bdql.Service.UserService;
import com.keer.bdql.pojo.WebResult;
import com.keer.bdql.pojo.vo.LoginRequest;
import com.keer.bdql.pojo.vo.RegisterRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;

    /**
     * 用户登录返回其公钥
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public WebResult login(@RequestBody LoginRequest request){
        logger.info("接收到请求：/user/login，登录信息："+request.toString());
        if(request.getPassword()==null||request.getPassword().equals("")||request.getUserName()==null||request.getUserName().equals("")){
            WebResult webResult=new WebResult();
            webResult.setCode(WebResult.CODE_ERROR_MISSPARAM);
            webResult.setMessage(WebResult.MSG_ERROR_MISSPARAM);
            return webResult;
        }
        return userService.login(request.getUserName(),request.getPassword());
    }

    /**
     * 用户注册
     * @param request
     * @return
     */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public WebResult register(@RequestBody RegisterRequest request){
        logger.info("接收到请求：/user/register，注册信息："+request.toString());
        if(request.getPassword()==null||request.getPassword().equals("")||request.getUserName()==null||request.getUserName().equals("")){
            WebResult webResult=new WebResult();
            webResult.setCode(WebResult.CODE_ERROR_MISSPARAM);
            webResult.setMessage(WebResult.MSG_ERROR_MISSPARAM);
            return webResult;
        }
        return userService.register(request.getUserName(),request.getPassword());
    }

    /**
     * 获取用户角色信息
     * 权限控制没有做，现在直接返回固定值
     * @return
     */
    @RequestMapping(value = "/getUserInfo/{userName}",method = RequestMethod.GET)
    public WebResult getUserInfo(@PathVariable String userName){
        logger.info("接收到请求：/getUserInfo/{userName} ，用户名："+userName);
        WebResult webResult=new WebResult();
        webResult.setMessage(WebResult.MSG_SUCCESS);
        webResult.setCode(WebResult.CODE_SUCCESS);
        Map data=new HashMap();
        data.put("introduction","I am "+userName);
        data.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("name","Super Admin");
        List roles=new ArrayList();
        roles.add("admin");
        data.put("roles",roles);
        webResult.setData(data);
        return webResult;
    }
}
