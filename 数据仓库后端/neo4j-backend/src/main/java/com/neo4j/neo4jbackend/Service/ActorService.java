package com.neo4j.neo4jbackend.Service;

import com.neo4j.neo4jbackend.Vo.ActorVo;
import com.neo4j.neo4jbackend.dao.ActorRepository;
import com.neo4j.neo4jbackend.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Collection;


@Service
public class ActorService {

    @Autowired
    ActorRepository actorRepository;

    public ActorVo getActorByTitle(String title){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Collection<Actor> actors= actorRepository.getActorByTitle(title);
        stopWatch.stop();
        ActorVo actorVo=new ActorVo();
        actorVo.setActors(actors);
        actorVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return actorVo;
    }

    public ActorVo getByDirector(String director){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Collection<Actor> actors=actorRepository.getByDirector(director);
        stopWatch.stop();
        ActorVo actorVo=new ActorVo();
        actorVo.setActors(actors);
        actorVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return actorVo;
    }
}
