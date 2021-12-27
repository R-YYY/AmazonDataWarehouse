package com.DataWarehouse.sys.service.impl;

import com.DataWarehouse.sys.entity.Act;
import com.DataWarehouse.sys.entity.Direct;
import com.DataWarehouse.sys.entity.Movie;
import com.DataWarehouse.sys.mapper.ActMapper;
import com.DataWarehouse.sys.mapper.MovieMapper;
import com.DataWarehouse.sys.service.ActService;
import com.DataWarehouse.sys.vo.ActorVo;
import com.DataWarehouse.sys.vo.DirectorVo;
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
public class ActServiceImpl extends ServiceImpl<ActMapper, Act> implements ActService {

    @Autowired
    ActMapper actMapper;

    @Autowired
    MovieMapper movieMapper;

    @Override
    public ActorVo getByTitle(String title){
        StopWatch stopWatch=new StopWatch();
        LambdaQueryWrapper<Movie> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Movie::getTitle,title);
        stopWatch.start("t");
        Movie movie=movieMapper.selectOne(wrapper);
        stopWatch.stop();
        if(movie==null) return null;
        else{
            String movieId=movie.getMovieId();
            LambdaQueryWrapper<Act> wrapper1=new LambdaQueryWrapper<>();
            wrapper1.eq(Act::getMovieId,movieId);
            stopWatch.start("t");
            List<Act> actors=actMapper.selectList(wrapper1);
            stopWatch.stop();
            ActorVo actorVo=new ActorVo();
            actorVo.setActors(actors);
            actorVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
            return actorVo;
        }
    }

    @Override
    public ActorVo getByTitle2(String title){
        LambdaQueryWrapper<Act> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Act::getMovieTitle,title);
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Act> actors=actMapper.selectList(wrapper);
        stopWatch.stop();
        ActorVo actorVo=new ActorVo();
        actorVo.setActors(actors);
        actorVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return actorVo;
    }
}
