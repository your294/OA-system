package com.chen.example.base;

/**
 * @author 86199
 */
public class ResultUtils {
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<>(20000, "OK", data);
    }
    public static <T> BaseResponse<T> error(int code, String errorMessage){
        return new BaseResponse<>(code, errorMessage, null);
    }

}
