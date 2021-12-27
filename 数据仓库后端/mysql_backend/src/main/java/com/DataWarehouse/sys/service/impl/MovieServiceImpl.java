package com.DataWarehouse.sys.service.impl;

import com.DataWarehouse.sys.entity.Movie;
import com.DataWarehouse.sys.mapper.MovieMapper;
import com.DataWarehouse.sys.service.MovieService;
import com.DataWarehouse.sys.vo.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LIAN
 * @since 2021-12-21
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Autowired
    MovieMapper movieMapper;

    @Override
    public MovieVo getByDate(DateVo dateVo){
        StopWatch stopWatch=new StopWatch();
        LambdaQueryWrapper<Movie> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Movie::getYear,dateVo.getYear());
        if(dateVo.getSeason()==null||dateVo.getSeason()==0||dateVo.getSeason()>4)
        {
            if(dateVo.getMonth()!=null&&""!=dateVo.getMonth()) wrapper.and(qw->qw.eq(Movie::getMonth,dateVo.getMonth()));
            if(dateVo.getDay()!=null&&""!=dateVo.getMonth()) wrapper.and(qw->qw.eq(Movie::getDay,dateVo.getDay()));
        }
        else
        {
            switch (dateVo.getSeason())
            {
                case 1: wrapper.and(qw->qw.ge(Movie::getMonth,1)).and(qw->qw.le(Movie::getMonth,3));break;
                case 2: wrapper.and(qw->qw.ge(Movie::getMonth,4)).and(qw->qw.le(Movie::getMonth,6));break;
                case 3: wrapper.and(qw->qw.ge(Movie::getMonth,7)).and(qw->qw.le(Movie::getMonth,9));break;
                case 4: wrapper.and(qw->qw.ge(Movie::getMonth,10)).and(qw->qw.le(Movie::getMonth,12));break;
                default:break;
            }
        }
        stopWatch.start();
        List<Movie> movies=movieMapper.selectList(wrapper);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByTitle(String title){
        StopWatch stopWatch=new StopWatch();
        LambdaQueryWrapper<Movie> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(Movie::getTitle,title);
        stopWatch.start();
        List<Movie> movies=movieMapper.selectList(wrapper);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByDirector(String name){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Movie> movies=movieMapper.getByDirector(name);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByActor(String name){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Movie> movies=movieMapper.getByActor(name);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByScore(double score){
        LambdaQueryWrapper<Movie> wrapper=new LambdaQueryWrapper<>();
        wrapper.ge(Movie::getScore,score);
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Movie> movies=movieMapper.selectList(wrapper);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByType(String type){
        LambdaQueryWrapper<Movie> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Movie::getType,type);
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Movie> movies=movieMapper.selectList(wrapper);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByPopular(){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Movie> movies=movieMapper.getByPopular();
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public ReCostVo getByRelation(RelationVo relationVo) {
        StopWatch stopWatch=new StopWatch();
        if("".equals(relationVo.getActorName()) && "".equals(relationVo.getDirectorName())){
            stopWatch.start();
            List<RelationVo> relations=movieMapper.getByRelation(relationVo.getTimes());
            stopWatch.stop();
            ReCostVo reCostVo=new ReCostVo();
            reCostVo.setRelations(relations);
            reCostVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
            return reCostVo;
        }
        else if("".equals(relationVo.getActorName())){
            stopWatch.start();
            List<RelationVo> relations=movieMapper.getByDirectorRelation(relationVo.getDirectorName(),relationVo.getTimes());
            stopWatch.stop();
            ReCostVo reCostVo=new ReCostVo();
            reCostVo.setRelations(relations);
            reCostVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
            return reCostVo;
        }
        else{
            stopWatch.start();
            List<RelationVo> relations=movieMapper.getByActorRelation(relationVo.getActorName(),relationVo.getTimes());
            stopWatch.stop();
            ReCostVo reCostVo=new ReCostVo();
            reCostVo.setRelations(relations);
            reCostVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
            return reCostVo;
        }
    }

    @Override
    public MovieVo getByCombine(CombineVo combineVo){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Movie> movies=movieMapper.getByCombine(combineVo.getYear(),combineVo.getDirector(),combineVo.getType());
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }
}
