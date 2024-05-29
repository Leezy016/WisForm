package com.wisform.entity;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {
    private boolean success;
    private String message;
    private List<Object> permissions;

    public ApiResponse(boolean success, String message,List<Object> permissions) {
        this.success = success;
        this.message = message;
        this.permissions = permissions; // 确保不为 null
    }
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.permissions = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public List<Object> getPermissions(){
        return permissions;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setPermissions(List<Object> permissions) {
        this.permissions = permissions;
    }

    public void apiout(){
        System.out.print(success);
        System.out.print(message);
        System.out.print(permissions);
    }
}
