package com.keer.bdql.pojo.vo;

/**
 * /query/queryByOperationAndTableName 接口入参
 */
public class QueryByOperationAndTableNameRequest {
    private String operation;
    private String tableName;
    private Integer pageNum;
    private Integer pageSize;

    public QueryByOperationAndTableNameRequest() {
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        return "QueryByOperationAndTableNameRequest{" +
                "operation='" + operation + '\'' +
                ", tableName='" + tableName + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                '}';
    }
}
