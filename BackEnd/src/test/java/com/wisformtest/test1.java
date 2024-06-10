package com.wisformtest;
import com.wisform.dao.FillFormRepository;
import org.neo4j.driver.*;
import com.wisform.dao.PersonRepository;
import com.wisform.entity.Answer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.annotation.Query;

import java.util.*;


import static org.neo4j.driver.Values.parameters;

@SpringBootTest
public class test1 {
        public static void main(String[] args) {
                String itemValue1 = "XXXXXXXX";
                String key = "journal";
                String uri = "bolt://localhost:7689";
                String user = "neo4j";
                String password = "12345678";
                try (Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password))) {
                        try (Session session = driver.session()) {
                                String query;
                                query = "MATCH (p:Project {name: $itemValue1})\n" +
                                        "RETURN\n" +
                                        "\tCASE\n" +
                                        "\tWHEN EXISTS(p[$key]) THEN p[$key]\n" +
                                        "\tELSE NULL\n" +
                                        "END AS itemValue";
                                Map<String, Object> parameters = new HashMap<>();
                                parameters.put("itemValue1", itemValue1);
                                parameters.put("key",key);
                                Result result = session.run(query,parameters);
                                System.out.print(result);
                        }
                }
        }
}

