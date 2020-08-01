package com.keer.bdql.pojo;

/**
 * controller 返回值
 * @param <T>
 */
public class WebResult<T> {
    public static final int SUCCESS = 20000;
    public static final int ERROR = 1;
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
