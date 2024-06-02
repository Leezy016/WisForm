package com.wisform.entity;

import java.util.List;

public class ApiFResponse {
    private boolean success;
    private String message;
    private List<String> titles;
    private List<String> Item;
    private List<String> ItemType;
    public ApiFResponse(boolean success, String message,List<String> formlist){
        this.titles = formlist;
        this.success = success;
        this.message = message;
    }
    public ApiFResponse(List<String> Item,boolean success, String message){
        this.success = success;
        this.message = message;
        this.Item = Item;
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

    public List<String> getTitles() {
        return titles;
    }

    public List<String> getItem() {
        return Item;
    }

    public ApiFResponse(boolean success, String message){
        this.success = success;
        this.message = message;
    }

}
