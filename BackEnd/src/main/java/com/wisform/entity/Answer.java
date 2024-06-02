package com.wisform.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Data
@NodeEntity(label="Answer")
public class Answer {
    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String title;
    @Property
    private List<String> item;
    @Property
    private List<String> value;

    public Answer(Long n,List<String> item,List<String>itemvalue){
        this.id = n;
        this.item = item;
        this.value = itemvalue;
    }
    public Long getId() {
        return id;
    }
}
