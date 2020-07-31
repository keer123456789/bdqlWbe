package com.keer.bdql.Controller;

import com.keer.bdql.pojo.WebResult;
import com.keer.bdql.pojo.vo.AddOneDataRequest;
import com.keer.bdql.pojo.vo.QueryByOperationAndTableNameRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
public class QueryController {

    /**
     * 查询页面，获取BigChainDB中所有的表名（asset，metadata）
     *
     * @return
     */
    @RequestMapping(name = "/getAllTable", method = RequestMethod.GET)
    public WebResult getAllTable() {
        return null;
    }

    /**
     * 查询页面，根据表名和表的类型（asset，metadata）查询数据
     * 分页查询
     *
     * @param request
     * @return
     */
    @RequestMapping(name = "/queryByOperationAndTableName", method = RequestMethod.POST)
    public WebResult queryByOperationAndTableName(@RequestBody QueryByOperationAndTableNameRequest request) {
        return null;
    }

    /**
     * 根据sql查询数据
     *
     * @param sql
     * @return
     */
    @RequestMapping(name = "/queryBySql", method = RequestMethod.POST)
    public WebResult querySql(@RequestBody String sql) {
        return null;
    }

    /**
     * 查询页面，添加按钮
     * 为某一个表添加一个数据
     *
     * @param request
     * @return
     */
    @RequestMapping(name = "/addOneData", method = RequestMethod.POST)
    public WebResult addOneData(@RequestBody AddOneDataRequest request) {
        return null;
    }
}
