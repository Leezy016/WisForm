package com.wisform.dao;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  AnswerRepository extends Neo4jRepository<Answer, Long> {
    @Query("MATCH (p:Person {name: $name})-[:FILL]->(f:Answer) RETURN f.id AS AnswerId")
    List<String> findAnswersByFillerName(@Param("name") String name);//id string函数int类型
    @Query("MATCH (a:Answer) WHERE a.id = $idParam RETURN a.item AS itemString")
    List<String> findItemById(@Param("idParam") String idParam);//string类型吗
    @Query("MATCH (a:Answer) WHERE a.id = $idParam RETURN a.value AS valueString")
    List<String> findValueById(@Param("idParam") String idParam);//string类型吗
    @Query("MATCH (p:FFormat {name: $name})-[:OWN]->(f:Answer) RETURN f.id AS AnswerId")
    List<String> findAnswersByFormatname(@Param("name") String name);

}
