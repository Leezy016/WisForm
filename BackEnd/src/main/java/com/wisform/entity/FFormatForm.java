package com.wisform.entity;

import java.util.Date;
import java.util.List;

public class FFormatForm {
    private String title;
    private String Publisher;
    //private Date LanchDate;
    private List<String> Item;
    private List<String> ItemType;

<<<<<<< HEAD
    private List<String>roleList;

    private boolean only;

    private  String  ddl;


    public FFormatForm(String title, String Publisher, List<String> Item,List<String> ItemType,List<String>roelList,boolean only,String ddl) {
        this.title = title;
        this.Publisher = Publisher;
        this.Item = Item;
        this.ItemType = ItemType;
        this.ddl = ddl;
        this.roleList = roleList;
        this.only = only;
=======

    public FFormatForm(String title, String Publisher, List<String> Item,List<String> ItemType) {
        this.title = title;
        this.Publisher = Publisher;
        //this.LanchDate = LanchDate;
        this.Item = Item;
        this.ItemType = ItemType;
>>>>>>> 2a7d56f7 (0531创建表单)
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
<<<<<<< HEAD

    public String getDDl() {
        return ddl;
    }

    public List<String> getRoleList() { return roleList; }

    public boolean getOnly() { return only; }

=======
>>>>>>> 2a7d56f7 (0531创建表单)
}

