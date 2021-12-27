package com.DataWarehouse.sys.service.impl;

import com.DataWarehouse.sys.entity.Direct;
import com.DataWarehouse.sys.mapper.DirectMapper;
import com.DataWarehouse.sys.service.DirectService;
import com.DataWarehouse.sys.vo.DirectorVo;
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
public class DirectServiceImpl extends ServiceImpl<DirectMapper, Direct> implements DirectService {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public DirectorVo getByTitle(String title){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        String sql1="SELECT movie_id FROM movie WHERE title=?";
        String movieId=jdbcTemplate.queryForObject(sql1,String.class,title);
        String sql2="SELECT director_name FROM direct WHERE movie_id=?";
        List<Map<String,Object>> directors=jdbcTemplate.queryForList(sql2,movieId);
        stopWatch.stop();
        DirectorVo directorVo=new DirectorVo();
        directorVo.setDirectors(directors);
        directorVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return directorVo;
    }
}
