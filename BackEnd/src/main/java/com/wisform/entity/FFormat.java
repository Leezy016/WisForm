package com.wisform.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.util.Date;
import java.util.List;

@Data
@NodeEntity(label="FFormat")
public class FFormat {
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private String Publisher;

    //@Property
    //private Date LanchDate;

    @Property
    private List<String> Item;

    @Property
    private List<String> ItemType;

    @Property
    private List<String> roleList;//权限列表
    @Property
    private Long only;//只能自己填

    @Property
    private  String  ddl;

    public FFormat(List<String> Item,List<String> ItemType,String Publisher,String name,List<String> roleList,Long only,String ddl) {
        this.name = name;
        this.Publisher = Publisher;
        //this.LanchDate = LanchDate;
        this.Item = Item;
        this.ItemType = ItemType;
        this.roleList = roleList;
        this.only = only;
        this.ddl = ddl;
    }
    public FFormat(String name){
        this.name = name;
    }

    public FFormat(){}


    public List<String>getItem(){
        return Item;
    }

    public List<String>getItemType(){
        return ItemType;
    }

    public String getName() {
        return name;
    }

    public String getDDl() {
        return ddl;
    }
}

