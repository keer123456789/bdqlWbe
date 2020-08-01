package com.keer.bdql.Service;

import com.keer.bdql.pojo.WebResult;

public interface QueryService {
    /**
     * 查询BigChainDB中的所有表
     * 通过mongodb查询
     *
     * @return
     */
    WebResult getAllTable();

    /**
     * 分页获取相应表的数据
     * @param tableName
     * @param operation
     * @param pageNum 从0计数
     * @param pageSize
     * @return
     */
    WebResult queryByOperationAndTableName(String tableName,String operation,Integer pageNum,Integer pageSize);
}
