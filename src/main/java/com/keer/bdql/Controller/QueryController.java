package com.keer.bdql.Controller;

import com.keer.bdql.Service.QueryService;
import com.keer.bdql.pojo.WebResult;
import com.keer.bdql.pojo.vo.AddOneDataRequest;
import com.keer.bdql.pojo.vo.QueryByOperationAndTableNameRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/query")
public class QueryController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    QueryService queryService;

    /**
     * 查询页面，获取BigChainDB中所有的表名（asset，metadata）
     * 返回结果
     * {
     *     "code":20000,
     *     "message":"success",
     *     "data":{
     *         "assetNames":["a","b",……],
     *         "metaDataName":["a","b",……]
     *     }
     * }
     *
     * @return
     */
    @RequestMapping(value = "/getAllTable", method = RequestMethod.GET)
    public WebResult getAllTable() {
        logger.info("接收到请求： /query/getAllTable, GET");
        return queryService.getAllTable();
    }

    /**
     * 查询页面，根据表名和表的类型（asset，metadata）查询数据
     * 分页查询
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/queryByOperationAndTableName", method = RequestMethod.POST)
    public WebResult queryByOperationAndTableName(@RequestBody QueryByOperationAndTableNameRequest request) {
        return null;
    }

    /**
     * 根据sql查询数据
     *
     * @param sql
     * @return
     */
    @RequestMapping(value = "/queryBySql", method = RequestMethod.POST)
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
    @RequestMapping(value = "/addOneData", method = RequestMethod.POST)
    public WebResult addOneData(@RequestBody AddOneDataRequest request) {
        return null;
    }
}
