### pattern
**nodes**  ()  
**labels**  (:Label)  
**relationship**  (:Label00)--(:Label01)  
**direction**  (:Label00)-->(:Label01)  (:Label00)<--(:Label01)  
**relationship type**  \[:type00\]  
**properties**  {property00: 'value00'}  


eg:  
```cypher
(m:Movie {title: 'Cloud Atlas'})<-[:ACTED_IN]-(p:Person)
```

### keywords
labels, property keys, and variables are **case-sensitive**.   
Cypher keywords are **not case-sensitive**.  

- Name labels using **CamelCase**.  
- Name property keys and variables using **camelCase**.  
- User **UPPERCASE** for Cypher keywords.  

##### MATCH  
```cypher

MATCH (p:Person {name: 'Tom Hanks'})
RETURN p

MATCH (p:Person {name: 'Tom Hanks'})
RETURN  p.born
```


##### WHERE  
```Cypher
MATCH (p:Person)
WHERE p.name = 'Tom Hanks'
RETURN p.born

// using WHERE to filter your queries is very powerful 
// because you can add more logic to your WHERE clause. 

MATCH (p:Person)
WHERE p.name = 'Tom Hanks' OR p.name = 'Rita Wilson'
RETURN p.name, p.born

match (p:professor) 
where id(p)=0 
set p.title="senior" return p

```

##### SET
```cypher

match (p:professor) 
where id(p)=0 
set p.title="senior" return p

```




##### 
```cypher



```


##### 
```cypher



```


##### 
```cypher



```