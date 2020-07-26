package com.keer.bdql.pojo.mongoDao;

import java.io.Serializable;
import java.util.Map;

/**
 * mongodb中 在asset和metadata数据集中存储的对象
 */
public class TableData implements Serializable {
    private String tableName;
    private Map tableData;

    public TableData() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map getTableData() {
        return tableData;
    }

    public void setTableData(Map tableData) {
        this.tableData = tableData;
    }
}
