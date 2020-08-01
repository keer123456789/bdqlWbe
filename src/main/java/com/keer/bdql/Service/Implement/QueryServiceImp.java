package com.keer.bdql.Service.Implement;

import com.keer.bdql.Controller.QueryController;
import com.keer.bdql.Service.QueryService;
import com.keer.bdql.dao.QueryDao;
import com.keer.bdql.pojo.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
        webResult.setMessage("success");
        webResult.setCode(WebResult.SUCCESS);
        logger.info("查询BigChainDB中所有的表名结果："+map.toString());
        return webResult;
    }
}
