package com.keer.bdql.dao;

import java.util.List;

public interface QueryDao {
    /**
     * 查询关键数据集中的所有表名
     * @return
     */
    List getAssetTableName();

    /**
     * 查询附加数据集中的所有表名
     * @return
     */
    List getMetadataTableName();
}
