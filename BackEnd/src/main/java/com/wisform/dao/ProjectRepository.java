package com.wisform.dao;

import com.wisform.entity.Person;
import com.wisform.entity.Project;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends Neo4jRepository<ProjectRepository, Long> {
    @Query("MATCH (p:Project {name: $itemValue1})\n" +
            "RETURN\n" +
            "\tCASE\n" +
            "\tWHEN p[$key] IS NOT NULL THEN p[$key]\n" +
            "\tELSE NULL\n" +
            "END AS itemValue")
    String getItemByKey(String itemValue1, String key);

    @Query("CREATE(n:Project)set n.name = $itemValue")
    void createProject(String itemValue);

    @Query("MATCH (p:Project)WHERE p.name = $value RETURN p.name")
    String findByKey(String value);

<<<<<<< HEAD
}
=======
}
>>>>>>> FrontEnd
