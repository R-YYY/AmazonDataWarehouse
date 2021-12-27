package com.DataWarehouse.sys.service.impl;

import com.DataWarehouse.sys.entity.Act;
import com.DataWarehouse.sys.entity.Direct;
import com.DataWarehouse.sys.entity.Movie;
import com.DataWarehouse.sys.mapper.ActMapper;
import com.DataWarehouse.sys.mapper.DirectMapper;
import com.DataWarehouse.sys.mapper.MovieMapper;
import com.DataWarehouse.sys.service.DirectService;
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
public class DirectServiceImpl extends ServiceImpl<DirectMapper, Direct> implements DirectService {

    @Autowired
    DirectMapper directMapper;

    @Autowired
    MovieMapper movieMapper;

    @Override
    public DirectorVo getByTitle(String title){
        StopWatch stopWatch=new StopWatch();
        LambdaQueryWrapper<Movie> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Movie::getTitle,title);
        stopWatch.start("t");
        Movie movie=movieMapper.selectOne(wrapper);
        stopWatch.stop();
        if(movie==null) return null;
        else {
            String movieId=movie.getMovieId();
            LambdaQueryWrapper<Direct> wrapper1=new LambdaQueryWrapper<>();
            wrapper1.eq(Direct::getMovieId,movieId);
            stopWatch.start("t");
            List<Direct> directors=directMapper.selectList(wrapper1);
            stopWatch.stop();
            DirectorVo directorVo=new DirectorVo();
            directorVo.setDirectors(directors);
            directorVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
            return directorVo;
        }
    }

    @Override
    public DirectorVo getByTitle2(String title){
        LambdaQueryWrapper<Direct> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Direct::getMovieTitle,title);
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Direct> directors=directMapper.selectList(wrapper);
        stopWatch.stop();
        DirectorVo directorVo=new DirectorVo();
        directorVo.setDirectors(directors);
        directorVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return directorVo;
    }
}
