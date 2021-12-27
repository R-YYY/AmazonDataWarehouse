package com.neo4j.neo4jbackend.Vo;

import com.neo4j.neo4jbackend.entity.Movie;
import lombok.Data;

import java.util.Collection;

@Data
public class MovieVo {

    private Collection<Movie> movies;

    private Long timeCost;
}
