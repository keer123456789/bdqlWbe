package com.keer.bdql.pojo;

/**
 * http 请求返回的统一格式
 * @param <T>
 */
public class ApiResponse<T> {
    public static final Integer SUCCESS_CODE=2000;
    public static final String SUCCESS_MESSAGE="success";
    public static final Integer ERROR_CODE=5008;
    public static final String ERROR_MESSAGE="参数错误";
    private Integer code;
    private T data;
    private String message;

    public ApiResponse(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public ApiResponse() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
