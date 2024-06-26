package com.wisform.entity;

import java.util.List;

public class ApiFResponse {
    private boolean success;
    private String message;
    private  String ans;
    private List<String> titles;
    private List<String> Item;
    private List<String> ItemValue;

    private boolean changeable;

    private int num;

    public ApiFResponse(boolean success, String message,int num){
        this.num = num;
        this.success = success;
        this.message = message;
    }
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

    public ApiFResponse(boolean success, String message,List<String> item, List<String> itemValue,boolean changeable) {
        this.success=success;
        this.message=message;
        this.Item = item;
        this.ItemValue = itemValue;
        this.changeable = changeable;
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
    public List<String> getItemValue(){
        return ItemValue;
    }

    public List<String> getTitles() {
        return titles;
    }

    public List<String> getItem() {
        return Item;
    }

    public String getAns(){
        return ans;
    }


    public ApiFResponse(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public  ApiFResponse(boolean success, String message,String ans){
        this.success = success;
        this.message = message;
        this.ans = ans;
    }

    public boolean getChangeable() {
        return changeable;
    }
}