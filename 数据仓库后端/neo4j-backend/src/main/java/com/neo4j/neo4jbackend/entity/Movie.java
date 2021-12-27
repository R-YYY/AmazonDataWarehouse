package com.neo4j.neo4jbackend.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.io.Serializable;

@Data
@Node(labels = "movie")
public class Movie implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String movieId;
    @Property
    private String title;
    @Property
    private Integer directorNum;
    @Property
    private Integer actorNum;
    @Property
    private String score;
    @Property
    private String format;
    @Property
    private String type;
    @Property
    private String year;
    @Property
    private String month;
    @Property
    private String day;
    @Property
    private Integer reviewNum;

}
