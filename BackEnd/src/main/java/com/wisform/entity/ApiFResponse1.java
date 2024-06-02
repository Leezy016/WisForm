package com.wisform.entity;

import java.util.List;

public class ApiFResponse1 {
    private boolean success;
    private String message;
    private int num;
    public ApiFResponse1(boolean success, String message,int num){
        this.num = num;
        this.success = success;
        this.message = message;
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

    public int getNum() {
        return num;
    }


}
