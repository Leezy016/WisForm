package com.wisform.entity;

import java.util.Date;
import java.util.List;

public class FFormatForm {
    private String title;
    private String Publisher;
    //private Date LanchDate;
    private List<String> Item;
    private List<String> ItemType;


    public FFormatForm(String title, String Publisher, List<String> Item,List<String> ItemType) {
        this.title = title;
        this.Publisher = Publisher;
        //this.LanchDate = LanchDate;
        this.Item = Item;
        this.ItemType = ItemType;
    }
    public String getname() {
        return title;
    }

    public String getPublisher() {
        return Publisher;
    }

    //public Date getLanchDate() {
    //    return LanchDate;
   // }

    public List<String> getItem() {return Item;}

    public List<String> getItemType(){
        return ItemType;
    }
}

