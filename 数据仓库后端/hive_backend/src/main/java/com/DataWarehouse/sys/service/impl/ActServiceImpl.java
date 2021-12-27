package com.DataWarehouse.sys.service.impl;

import com.DataWarehouse.sys.entity.Act;
import com.DataWarehouse.sys.entity.Movie;
import com.DataWarehouse.sys.mapper.ActMapper;
import com.DataWarehouse.sys.mapper.MovieMapper;
import com.DataWarehouse.sys.service.ActService;
import com.DataWarehouse.sys.vo.ActorVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;

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
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public ActorVo getByTitle(String title){
        StopWatch stopWatch=new StopWatch();
        String sql1="SELECT movie_id FROM movie WHERE title=?";
        stopWatch.start();
        String movieId=jdbcTemplate.queryForObject(sql1,String.class,title);
        String sql2="SELECT actor_name FROM act WHERE movie_id=?";
        List<Map<String,Object>> actors=jdbcTemplate.queryForList(sql2,movieId);
        stopWatch.stop();
        ActorVo actorVo=new ActorVo();
        actorVo.setActors(actors);
        actorVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return actorVo;
    }
}
