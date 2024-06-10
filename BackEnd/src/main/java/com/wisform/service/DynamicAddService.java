package com.wisform.service;
import org.neo4j.driver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.neo4j.driver.Values.parameters;
@Service
public class DynamicAddService {

    private final Driver driver;

    @Autowired
    public DynamicAddService(Neo4jProperties neo4jProperties) {
        this.driver = GraphDatabase.driver(neo4jProperties.getUri(),
                AuthTokens.basic(neo4jProperties.getUsername(),
                        neo4jProperties.getPassword()));
    }

    public void addPro(String name,List<String> item, List<String> itemValue) {
        try (Session session = driver.session()) {
            int size = item.size();
            String query;
            for (int i = 0; i < size; i++){
                query = "MATCH (p:Project {name: $name}) SET p." + item.get(i) + " = $value";
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("name", name);
                parameters.put("value",itemValue.get(i));
                Result result = session.run(query,parameters);
            }
        }
    }

    public void addPer(String name,List<String> item, List<String> itemValue) {
        try (Session session = driver.session()) {
            int size = item.size();
            String query;
            for (int i = 0; i < size; i++){
                query = "MATCH (p:Person {name: $name}) SET p." + item.get(i) + " = $value";
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("name", name);
                parameters.put("value",itemValue.get(i));
                Result result = session.run(query,parameters);
            }
        }
    }
}

