package com.wisform.entity;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {
    private boolean success;
    private String message;
    private List<Object> permissions;
    private String role;
    private List<String> department;

    private List<String> names;
    private long ex;

    public ApiResponse(boolean success, String message,List<String> names,long ex){
        this.success = success;
        this.message = message;
        this.names = names;
        this.ex = ex;
    }


    public ApiResponse(boolean success, String message,List<Object> permissions) {
        this.success = success;
        this.message = message;
        this.permissions = permissions; // 确保不为 null
    }

    public ApiResponse(boolean success, String message,String role,List<Object> permissions,List<String> department) {
        this.success = success;
        this.message = message;
        this.permissions = permissions;
        this.role = role;
        this.department = department;
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
    public String getRole(){return role;}
    public List<String> getDepartment(){return department;}

    public List<Object> getPermissions(){
        return permissions;
    }

    public List<String>getNames(){
        return names;
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
