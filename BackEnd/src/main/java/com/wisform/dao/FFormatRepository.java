package com.wisform.dao;

import com.wisform.entity.FFormat;
import com.wisform.entity.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FFormatRepository extends Neo4jRepository<FFormatRepository, Long> {//是long吗
    @Query("CREATE (p:FFormat {name: $name, Publisher: $Publisher,Item: $Item,ItemType: $ItemType}) RETURN p")
    FFormat saveFFormat(@Param("name") String name, @Param("Publisher") String Publisher, @Param("Item") List<String> Item, @Param("ItemType") List<String> ItemType);
    @Query("MATCH (p:FFormat) WHERE p.name = $name RETURN p")
    FFormat findByName(String name);

    @Query("MATCH (n:FFormat {name: $name})UNWIND n.Item AS itemValue RETURN itemValue")
    List<String> getItemByName(String name);
    @Query("MATCH (p:Person {name: $name}), (f:FFormat {name: $title})MERGE (p)-[:PUBLISH]->(f)")
    void Relate_Publish(String name,String title);
}