package com.neo4j.neo4jbackend.Service;

import com.neo4j.neo4jbackend.Vo.CombineVo;
import com.neo4j.neo4jbackend.Vo.DateVo;
import com.neo4j.neo4jbackend.Vo.MovieVo;
import com.neo4j.neo4jbackend.dao.MovieRepository;
import com.neo4j.neo4jbackend.entity.Movie;
import javafx.scene.paint.Stop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Collection;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public MovieVo getByTitle(String title){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Collection<Movie> movies=movieRepository.getByTitle(title);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    public MovieVo getByScore(String score){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Collection<Movie> movies=movieRepository.getByScore(score);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    public MovieVo getByType(String type){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Collection<Movie> movies=movieRepository.getByType(type);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    public MovieVo getByPopular(){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Collection<Movie> movies=movieRepository.getByPopular();
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    public MovieVo getByActor(String actor){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Collection<Movie> movies=movieRepository.getByActor(actor);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    public MovieVo getByDirector(String director){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Collection<Movie> movies=movieRepository.getByDirector(director);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    public MovieVo getByDate(DateVo dateVo){
        StopWatch stopWatch=new StopWatch();
        Collection<Movie> movies=null;
        if(dateVo.getSeason() != null && dateVo.getSeason()>0&&dateVo.getSeason()<5){
            switch (dateVo.getSeason()){
                case 1:{
                    stopWatch.start();
                    movies=movieRepository.getBySeason1(dateVo.getYear());
                    stopWatch.stop();
                    break;
                }
                case 2:{
                    stopWatch.start();
                    movies=movieRepository.getBySeason2(dateVo.getYear());
                    stopWatch.stop();
                    break;
                }
                case 3:{
                    stopWatch.start();
                    movies=movieRepository.getBySeason3(dateVo.getYear());
                    stopWatch.stop();
                    break;
                }
                case 4:{
                    stopWatch.start();
                    movies=movieRepository.getBySeason4(dateVo.getYear());
                    stopWatch.stop();
                    break;
                }
                default:break;
            }
        }else{
            if(dateVo.getMonth()==null)
            {
                stopWatch.start();
                movies=movieRepository.getByDate1(dateVo.getYear());
                stopWatch.stop();
            }
            else if(dateVo.getDay()==null)
            {
                stopWatch.start();
                movies=movieRepository.getByDate2(dateVo.getYear(),dateVo.getMonth());
                stopWatch.stop();
            }
            else
            {
                stopWatch.start();
                movies=movieRepository.getByDate(dateVo.getYear(),dateVo.getMonth(),dateVo.getDay());
                stopWatch.stop();
            }
        }
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    public MovieVo getByCombine(CombineVo combineVo){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        Collection<Movie> movies=movieRepository.getByCombine(combineVo.getYear(),combineVo.getDirector(),combineVo.getType());
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }
}
