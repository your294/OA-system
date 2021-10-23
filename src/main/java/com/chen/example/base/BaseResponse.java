package com.chen.example.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 86199
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private int code;

    private String message;

    private T data;
}
