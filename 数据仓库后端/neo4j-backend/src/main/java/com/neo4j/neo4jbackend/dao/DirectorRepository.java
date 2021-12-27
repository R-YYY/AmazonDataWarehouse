package com.neo4j.neo4jbackend.dao;

import com.neo4j.neo4jbackend.entity.Director;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface DirectorRepository extends Neo4jRepository<Director,Long> {
    @Query("MATCH (x:director)-[r:DIRECT]->(y:movie) WHERE y.title={title} return x")
    Collection<Director> getByTitle(@Param("title") String title);

    @Query("MATCH (a:actor)-[r:COOPERATE]-(d:director) WHERE a.name={actor} return d")
    Collection<Director> getByActor(@Param("actor") String actor);
}
