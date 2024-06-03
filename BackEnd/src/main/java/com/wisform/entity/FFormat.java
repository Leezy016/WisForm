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
    public FFormat(List<String> Item,List<String> ItemType,String Publisher,String name ) {
        this.name = name;
        this.Publisher = Publisher;
        //this.LanchDate = LanchDate;
        this.Item = Item;
        this.ItemType = ItemType;
    }
    public FFormat(String name){
        this.name = name;
    }
    public FFormat(){
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
}

