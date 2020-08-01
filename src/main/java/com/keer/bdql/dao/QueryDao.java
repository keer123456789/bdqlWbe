package com.keer.bdql.dao;

import com.keer.bdql.pojo.Table;

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

    /**
     * 根据operation的值确定是数据集，根据tableName进行查询，并进行分页
     * @param tableName
     * @param operation CREATE or TRANSFER
     * @param pageNum
     * @param pageSize
     * @return
     */
    Table getTableData(String tableName, String operation, int pageNum, int pageSize);

    /**
     * 计算相应表下的数据量
     * @param tableName
     * @param operation CREATE or TRANSFER
     * @return
     */
    long countTableData(String tableName,String operation);
}
