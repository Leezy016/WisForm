package com.wisform.dao;

import com.wisform.entity.Answer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FillFormRepository extends Neo4jRepository<FillFormRepository, Long> {
    @Query("MATCH (f:FFormat) RETURN f.name")
    List<String> formlist();

    @Query("Create (f:Answer{id : $id,title: $title,filler:$filler,item:$item,value:$value}) RETURN f")
    Answer createById(Long id,String title,String filler,List<String> item,List<String> value);

    //@Query("MATCH (p:Person {name: $filler}), (f:Answer {filler: $filler})CREATE (p)-[:FILL]->(f)")
    @Query("MATCH (p:Person {name: $filler})OPTIONAL MATCH (f:Answer {filler: $filler})WHERE p.name = f.filler MERGE (p)-[:FILL]->(f)")
    void Relate_Fill(String filler);

    @Query("MATCH (p:Person {name: $filler}), (f:FFormat {name: $title})MERGE (p)-[:CONTRIBUTE]->(f)")
    void Relate_Contribute(String title,String filler);

    //@Query("MATCH (f:FFormat {name: $title}), (a:Answer {title: $title})CREATE (f)-[:OWN]->(a)")
    @Query("MATCH (f:FFormat {name: $title})OPTIONAL MATCH(a:Answer {title: $title})WHERE f.name = a.title MERGE (f)-[:OWN]->(a)")
    void Relate_Own(String title);

    @Query("MATCH (a:Answer)RETURN max(a.id) AS maxId")
    Long global_id();

    @Query("MATCH (n:FFormat) WHERE ANY(id IN n.roleList WHERE id = $role) RETURN n.name AS name")
    List<String> findFFormatByRole(@Param("role") String role);
}
