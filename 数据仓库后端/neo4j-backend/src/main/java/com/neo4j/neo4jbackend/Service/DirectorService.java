package com.neo4j.neo4jbackend.Service;

import com.neo4j.neo4jbackend.Vo.DirectorVo;
import com.neo4j.neo4jbackend.dao.DirectorRepository;
import com.neo4j.neo4jbackend.entity.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Collection;

@Service
public class DirectorService {
    @Autowired
    DirectorRepository directorRepository;

    public DirectorVo getByTitle(String title){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Collection<Director> directors= directorRepository.getByTitle(title);
        stopWatch.stop();
        DirectorVo directorVo=new DirectorVo();
        directorVo.setDirectors(directors);
        directorVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return directorVo;
    }

    public DirectorVo getByActor(String actor){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Collection<Director> directors=directorRepository.getByActor(actor);
        stopWatch.stop();
        DirectorVo directorVo=new DirectorVo();
        directorVo.setDirectors(directors);
        directorVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return directorVo;
    }
}
