package com.neo4j.neo4jbackend.Vo;

import com.neo4j.neo4jbackend.entity.Director;
import lombok.Data;

import java.util.Collection;

@Data
public class DirectorVo {

    private Collection<Director> directors;

    private Long timeCost;
}
