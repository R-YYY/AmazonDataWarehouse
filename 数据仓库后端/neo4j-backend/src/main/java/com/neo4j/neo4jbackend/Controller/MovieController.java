package com.neo4j.neo4jbackend.Controller;

import com.neo4j.neo4jbackend.Service.MovieService;
import com.neo4j.neo4jbackend.Vo.CombineVo;
import com.neo4j.neo4jbackend.Vo.DateVo;
import com.neo4j.neo4jbackend.Vo.MovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController{

    @Autowired
    MovieService movieService;

    @GetMapping("/getbytitle")
    public MovieVo getByTitle(String title){
        return movieService.getByTitle(title);
    }

    @GetMapping("/getbyscore")
    public MovieVo getByScore(String score){
        return movieService.getByScore(score);
    }

    @GetMapping("/getbytype")
    public MovieVo getByType(String type){
        return movieService.getByType(type);
    }
    
    @GetMapping("/getbypopular")
    public MovieVo getByPopular(){
        return movieService.getByPopular();
    }

    @GetMapping("/getbyactor")
    public MovieVo getByActor(String actor){
        return movieService.getByActor(actor);
    }

    @GetMapping("/getbydirector")
    public MovieVo getByDirector(String director){
        return movieService.getByDirector(director);
    }

    @PostMapping("/getbydate")
    public MovieVo getByDate(DateVo dateVo){
        return movieService.getByDate(dateVo);
    }

    @PostMapping("/getbycombine")
    public MovieVo getByCombine(CombineVo combineVo){
        return movieService.getByCombine(combineVo);
    }
}