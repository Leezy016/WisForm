package com.wisform.entity;
import lombok.Data;
import org.neo4j.ogm.annotation.*;
import java.io.Serializable;
import javax.annotation.sql.DataSourceDefinition;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.io.Serializable;
import java.util.List;

@Data
@NodeEntity(label="Person")
public class Person implements Serializable {
    @Id @GeneratedValue
    private Long id;

    @Property
    private String name;

    @Property
    private String identity;

    @Property
    private String passwd;

    @Property
    private List<String> department;;

    public Person(String name, String identity, String passwd) {
        this.name = name;
        this.identity = identity;
        this.passwd = passwd;
        //this.department = department;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public String getIdentity() {
        return this.identity;
    }

    public String getUsername() {
        return this.name;
    }
    public List<String> getDepartment() {
        return department;
    }

    public void setDepartment(String a) {
        this.department.add(a);
    }
    public void setDepartment(List<String> department) {
        this.department = department;
    }
}


