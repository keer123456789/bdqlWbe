package com.keer.bdql.pojo;

/**
 * controller 返回值
 * @param <T>
 */
public class WebResult<T> {
    public static final int CODE_SUCCESS = 20000;
    public static final int CODE_ERROR_MISSPARAM = 30001;
    public static final int CODE_ERROR_PARAMERROR=30002;
    public static final int ERROR = 1;
    public static final int BDQL_ERROR_GRAMMAR=101;

    public static final String MSG_SUCCESS="success";
    public static final String MSG_ERROR_MISSPARAM="缺失参数，请检查";
    public static final String MSG_ERROR_PARAMERROR="缺失错误，请检查";
    public static final String MSG_ERROR="fail";
    public static final String MSG_BDQL_ERROR_GRAMMAR="BDQL 语法错误";
    private int code;
    private T data;
    private String message;

    public WebResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
