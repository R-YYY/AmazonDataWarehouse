package com.neo4j.neo4jbackend.dao;

import com.neo4j.neo4jbackend.entity.Actor;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface ActorRepository extends Neo4jRepository<Actor,Long> {
    @Query("MATCH (x:actor)-[r:ACT]->(y:movie) WHERE y.title={title} return x")
    Collection<Actor> getActorByTitle(@Param("title") String title);

    @Query("MATCH (a:actor)-[r:COOPERATE]-(d:director) WHERE d.name={director} return a")
    Collection<Actor> getByDirector(@Param("director") String director);
}
