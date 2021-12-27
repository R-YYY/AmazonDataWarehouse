package com.neo4j.neo4jbackend.Vo;

import com.neo4j.neo4jbackend.entity.Actor;
import lombok.Data;

import java.util.Collection;

@Data
public class ActorVo {

    private Collection<Actor> actors;

    private Long timeCost;
}
