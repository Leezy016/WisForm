package com.wisform.entity;

import java.util.List;

public class AnswerForm {
    private String title;
    private String filler;
    private List<String> Item;
    private List<String> ItemValue;

    public AnswerForm(String title,String filler,List<String> Item,List<String> ItemValue){
        this.title = title;
        this.filler = filler;
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
}
