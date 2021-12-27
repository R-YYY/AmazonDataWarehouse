package com.neo4j.neo4jbackend.Controller;

import com.neo4j.neo4jbackend.Service.ActorService;
import com.neo4j.neo4jbackend.Vo.ActorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/act")
public class ActorController {

    @Autowired
    ActorService actorService;

    @GetMapping("/getbytitle")
    public ActorVo getActorByTitle(String title){
        return actorService.getActorByTitle(title);
    }

    @GetMapping("/getbydirector")
    public ActorVo getByDirector(String director){
        return actorService.getByDirector(director);
    }
}
