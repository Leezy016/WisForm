package com.wisform.dao;

import com.wisform.entity.FFormat;
import com.wisform.entity.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FFormatRepository extends Neo4jRepository<FFormatRepository, Long> {//是long吗
    //@Query("CREATE (p:FFormat {name: $name, Publisher: $Publisher,Item: $Item,ItemType: $ItemType,ddl: $ddl}) RETURN p")
    //void saveFFormat(@Param("name") String name, @Param("Publisher") String Publisher, @Param("Item") List<String> Item, @Param("ItemType") List<String> ItemType);
    @Query("CREATE (p:FFormat {name: $name, Publisher: $Publisher,Item: $Item,ItemType: $ItemType,roleList: $roleList,only: $only ,ddl:$ddl}) RETURN p")
    void saveFFormat(@Param("name") String name, @Param("Publisher") String Publisher, @Param("Item") List<String> Item, @Param("ItemType") List<String> ItemType,@Param("roleList") List<String> roleList,@Param("only") boolean only, @Param("ddl")LocalDate ddl);

    @Query("MATCH (p:FFormat) WHERE p.name = $name RETURN p")
    FFormat findByName(String name);

    @Query("MATCH (n:FFormat {name: $name})UNWIND n.Item AS itemValue RETURN itemValue")
    List<String> getItemByName(String name);

    @Query("MATCH (p:Person {name: $name}), (f:FFormat {name: $title})MERGE (p)-[:PUBLISH]->(f)")
    void Relate_Publish(String name,String title);

    //新增
    @Query("MATCH (p:Person {name: $name})-[:PUBLISH]->(f:FFormat) RETURN f.name AS formatName")
    List<String> findFormatsByPublisherName(@Param("name") String name);
    //新增
    @Query("MATCH (p:FFormat) RETURN p.name AS name")
    List<String> findAllNames();
    //xin 由人名查找填过的问卷,FILLFORM
    @Query("MATCH (p:Person {name: $name})-[:CONTRIBUTE]->(f:FFormat) RETURN f.name AS FFormatName")
    List<String> findFormatByPersonName(@Param("name") String name);

    @Query("MATCH(f:FFormat{name:$title}) RETURN f.only")
    String OnlyByName(String title);
}