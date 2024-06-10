package com.wisform.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AnswerForm {
    private String title;
    private String filler;
    @JsonProperty("Item")
    private List<String> Item;
    @JsonProperty("ItemValue")
    private List<String> ItemValue;

    private Long id;

    public AnswerForm(String title,String filler,List<String> Item,List<String> ItemValue){
        this.title = title;
        this.filler = filler;
        this.Item = Item;
        this.ItemValue = ItemValue;
    }
    public  AnswerForm(){}
    public AnswerForm(Long id,List<String> Item,List<String> ItemValue){
        this.id = id;
        this.Item = Item;
        this.ItemValue = ItemValue;
    }

    public String getTitle() {
        return title;
    }

    public String getFiller() {
        return filler;
    }

    public List<String> getItem(){
        return Item;
    }
    public List<String> getValue(){
        return ItemValue;
    }

    public Long getId() {
        return id;
    }

    public void setItem(List<String>item){
        this.Item = item;
    }

    public void setItemValue(List<String>item){
        this.ItemValue = item;
    }
}
