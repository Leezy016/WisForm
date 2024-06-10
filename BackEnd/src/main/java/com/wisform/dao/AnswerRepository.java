package com.wisform.dao;

import com.wisform.entity.Answer;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface  AnswerRepository extends Neo4jRepository<Answer, Long> {
    @Query("MATCH (p:Person {name: $name})-[:FILL]->(f:Answer) RETURN f.id AS AnswerId")
    List<String> findAnswersByFillerName(@Param("name") String name);//id string函数int类型
    @Query("MATCH (p:FFormat {name: $name})-[:OWN]->(f:Answer) RETURN f.id AS AnswerId")
    List<String> findAnswersByFormatname(@Param("name") String name);

    @Query("MATCH (n:Answer {id: $idParam,title: $title})UNWIND n.item AS itemString RETURN itemString")
    List<String> findItemById(@Param("idParam") long idParam ,@Param("title") String title);//string类型吗

    @Query("MATCH (n:Answer {id: $idParam,title: $title})UNWIND n.value AS itemString RETURN itemString")
    List<String> findValueById(@Param("idParam") long idParam ,@Param("title") String title);//string类型吗

    @Query("MATCH (f:FFormat{name:$name}) RETURN f.ddl AS date")
    LocalDate checkdate(String name);

    @Query("MATCH (a:Answer {id:$id})set a.item = $item,a.value = $itemValue ")
    void coverById(Long id, List<String> item, List<String> itemValue);

    @Query("MATCH (a:Answer) " +
            "WITH a.item AS items, a.value AS values " +
            "UNWIND RANGE(0, SIZE(items) - 1) AS index " +
            "WITH items[index] AS item, values[index] AS correspondingValue " +
            "WHERE item CONTAINS $siterm " +
            "RETURN correspondingValue")
    List<String> findValuesByItemContaining(@Param("siterm") String siterm);
}