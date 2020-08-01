package com.keer.bdql.Service.Implement;

import com.keer.bdql.Controller.QueryController;
import com.keer.bdql.Service.QueryService;
import com.keer.bdql.dao.QueryDao;
import com.keer.bdql.pojo.Table;
import com.keer.bdql.pojo.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryServiceImp implements QueryService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    QueryDao queryDao;

    /**
     * 查询BigChainDB中关键数据集和附加数据集的表集合
     *
     * @return
     */
    @Override
    public WebResult getAllTable() {
        WebResult webResult = new WebResult();
        List assetNames = queryDao.getAssetTableName();
        List metaDataName = queryDao.getMetadataTableName();
        HashMap map = new HashMap();
        map.put("assetNames", assetNames);
        map.put("metaDataName", metaDataName);
        webResult.setData(map);
        webResult.setMessage(WebResult.MSG_SUCCESS);
        webResult.setCode(WebResult.CODE_SUCCESS);
        logger.info("查询BigChainDB中所有的表名结果：" + map.toString());
        return webResult;
    }

    /**
     * 分页查询相应表的数据
     *
     * @param tableName
     * @param operation
     * @param pageNum   从0计数
     * @param pageSize
     * @return
     */
    @Override
    public WebResult queryByOperationAndTableName(String tableName, String operation, Integer pageNum, Integer pageSize) {
        Table table = queryDao.getTableData(tableName, operation, pageNum, pageSize);
        logger.info("查询表的数据结果：" + table.toString());
        long total = queryDao.countTableData(tableName, operation);
        logger.info("查询表数据总量，结果：" + total);
        Map data = new HashMap();
        data.put("field", table.getColumnName());
        data.put("data", table.getData());
        data.put("total", total);

        WebResult webResult = new WebResult();
        webResult.setMessage(WebResult.MSG_SUCCESS);
        webResult.setCode(WebResult.CODE_SUCCESS);
        webResult.setData(data);
        logger.info("分页查询返回结果：" + webResult.toString());
        return webResult;
    }
}
