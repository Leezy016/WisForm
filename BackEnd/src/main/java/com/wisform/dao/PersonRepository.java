package com.wisform.dao;

import com.wisform.entity.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/*@Repository//注解标记，表示这是一个 DAO 组件，用于访问数据存储。Spring 容器会自动扫描并加载这个 DAO 组件，并将其纳入到 Spring 的上下文中进行管理。
/*public interface PersonRepository extends Neo4jRepository<Person,Long>{
}
public interface PersonRepository extends Neo4jRepository<Person,Long>{
    boolean existsByName(String name);
}*/
@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    @Query("MATCH (p:Person) WHERE p.name = $name AND p.identity = $identity RETURN p")
    Optional<Person> findByNameAndIdentity(String name, String identity);
    @Query("MATCH (p:Person) WHERE p.name = $username RETURN p")
    Person findByName(String username);
    @Query("CREATE (p:Person {name: $name, identity: $identity, passwd: $passwd,department: $department,enable: 1}) RETURN p")
    Person saveUser(@Param("name") String name, @Param("identity") String identity, @Param("passwd") String passwd,@Param("department") List<String> department);

    //@Query("MATCH (n:roleControl) RETURN n[$a] AS value")
    //List<Object> getroleControl(@Param("a") String propertyName);

    @Query("MATCH (n:roleControl) WITH n[$a] AS value UNWIND value AS flattenedValue RETURN flattenedValue")
    List<Object> getroleControl(@Param("a") String propertyName);

    //新 由名字查身份
    @Query("MATCH (p:Person) WHERE p.name = $name RETURN p.identity")
    String findIdentityByName(String name);
    //新 由身份查人名
    @Query("MATCH (p:Person) WHERE p.identity = $identity RETURN p.name")
    List<String> findNameByIdentity(String identity);

    @Query("MATCH (p:Person)WHERE p.identity <> $role RETURN p.name")
    List<String> findPDean(String role);

    @Query("MATCH (p:Person)WHERE p.name = $name RETURN p.department")
    List<String> findDepartmentByName(String name);
    @Query("MATCH (p:Person)WHERE p.identity = 'teacher' AND ($de IN p.department ) RETURN p.name")
    List<String> findPChair(String de);

    @Query("MATCH (p:Person{name:$username})SET p.enable=0 ")
    void unableByName(String username);

    @Query("MATCH (p:Person {name: $itemValue1})"+
            "RETURN\n"+
                "CASE\n"+
                    "WHEN p[$key] IS NOT NULL THEN p[$key]\n"+
                    "ELSE NULL\n"+
                "END AS itemValue")
    String getItemByKey(String itemValue1, String key);
    @Query("MATCH (p:Person {name: $name}) " +
            "SET p.$key = $value ")
    void updatePerson(@Param("name") String name, @Param("key") String key, @Param("value") Object value);

    @Query("CREATE(n:Person)set n.name = $itemValue")
    void createPerson(String itemValue);

    @Query("MATCH (p:Person)WHERE p.name = $value RETURN p.name")
    String findByKey(String value);

    @Query("MATCH (p:Person{name:$username})SET p.enable=1 ")
    void ableByName(String username);
}
