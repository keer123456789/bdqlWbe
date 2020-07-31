package com.keer.bdql.pojo.vo;

import java.util.Map;

/**
 * /query/addOneData 接口入参
 */
public class AddOneDataRequest {
    private String tableName;
    private String operation;
    private Map data;

    public AddOneDataRequest() {
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AddOneDataRequest{" +
                "tableName='" + tableName + '\'' +
                ", operation='" + operation + '\'' +
                ", data=" + data +
                '}';
    }
}
