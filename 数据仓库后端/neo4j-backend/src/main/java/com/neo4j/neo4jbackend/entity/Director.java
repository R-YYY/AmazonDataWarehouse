package com.neo4j.neo4jbackend.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Data
@Node(labels = "director")
public class Director {
    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String name;
}
