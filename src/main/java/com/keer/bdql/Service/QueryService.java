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
}
