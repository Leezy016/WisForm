package com.wisform.entity;
import java.util.List;
public class TwoArraysResponse {
    private boolean success;
    private String message;
    private List<String> array1;
    private List<String> array2;

    // 构造函数、getter和setter方法...

    public TwoArraysResponse(boolean success, String message,List<String> array1, List<String> array2) {
        this.success=success;
        this.message=message;
        this.array1 = array1;
        this.array2 = array2;
    }

    // ... getter和setter方法
}