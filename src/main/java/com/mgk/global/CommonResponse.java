package com.mgk.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class CommonResponse<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(Result.OK.getCode(),Result.OK.getMsg(),data);
    }

    public static <T> CommonResponse<T> success() {
        return new CommonResponse<>(Result.OK.getCode(),Result.OK.getMsg(),null);
    }
}
