package com.DataWarehouse.sys.service.impl;

import com.DataWarehouse.sys.entity.Movie;
import com.DataWarehouse.sys.mapper.MovieMapper;
import com.DataWarehouse.sys.service.MovieService;
import com.DataWarehouse.sys.vo.CombineVo;
import com.DataWarehouse.sys.vo.DateVo;
import com.DataWarehouse.sys.vo.MovieVo;
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
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public MovieVo getByDate(DateVo dateVo){
        StopWatch stopWatch=new StopWatch();
        List<Map<String,Object>> movies=null;
        if(dateVo.getSeason() == null || dateVo.getSeason()==0||dateVo.getSeason()>4)
        {
            if(dateVo.getMonth() == null)
            {
                String sql="SELECT * FROM movie WHERE year=?";
                stopWatch.start();
                movies=jdbcTemplate.queryForList(sql,dateVo.getYear());
                stopWatch.stop();
            }
            if(dateVo.getMonth()!=null &&dateVo.getDay()==null)
            {
                stopWatch.start();
                String sql="SELECT * FROM movie WHERE year=? and month=?";
                movies=jdbcTemplate.queryForList(sql,dateVo.getYear(),dateVo.getMonth());
                stopWatch.stop();
            }
            if(dateVo.getDay()!=null&&dateVo.getDay()!=null)
            {
                stopWatch.start();
                String sql="SELECT * FROM movie WHERE year=? and month=? and day=?";
                movies=jdbcTemplate.queryForList(sql,dateVo.getYear(),dateVo.getMonth(),dateVo.getDay());
                stopWatch.stop();
            }
        }
        else
        {
            switch (dateVo.getSeason())
            {
                case 1:
                {
                    String sql="SELECT * FROM movie WHERE year=? and month>=1 and month<=3";
                    stopWatch.start();
                    movies=jdbcTemplate.queryForList(sql,dateVo.getYear());
                    stopWatch.stop();
                    break;
                }
                case 2:
                {
                    stopWatch.start();
                    String sql="SELECT * FROM movie WHERE year=? and month>=4 and month<=6";
                    movies=jdbcTemplate.queryForList(sql,dateVo.getYear());
                    stopWatch.stop();
                    break;
                }
                case 3:
                {
                    stopWatch.start();
                    String sql="SELECT * FROM movie WHERE year=? and month>=7 and month<=9";
                    movies=jdbcTemplate.queryForList(sql,dateVo.getYear());
                    stopWatch.stop();
                    break;
                }
                case 4:
                {
                    stopWatch.start();
                    String sql="SELECT * FROM movie WHERE year=? and month>=10 and month<=12";
                    movies=jdbcTemplate.queryForList(sql,dateVo.getYear());
                    stopWatch.stop();
                    break;
                }
                default:break;
            }
        }
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByTitle(String title){
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Map<String,Object>> movies=jdbcTemplate.queryForList("SELECT * FROM movie where title=?",title);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByDirector(String name){
        String sql="SELECT title,score FROM movie,direct where director_name=? AND direct.movie_id=movie.movie_id";
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Map<String,Object>> movies=jdbcTemplate.queryForList(sql,name);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByActor(String name){
        String sql="SELECT title,score FROM movie,act where actor_name=? AND act.movie_id=movie.movie_id";
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Map<String,Object>> movies=jdbcTemplate.queryForList(sql,name);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByScore(double score){
        String sql="SELECT * FROM movie where score>=?";
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Map<String,Object>> movies=jdbcTemplate.queryForList(sql,score);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByType(String type){
        String sql="SELECT * FROM movie where type=?";
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Map<String,Object>> movies=jdbcTemplate.queryForList(sql,type);
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByCombine(CombineVo combineVo){
        String sql="SELECT * FROM movie,direct where director_name=? AND movie.year=? AND movie.type=? AND direct.movie_id=movie.movie_id";
        StopWatch stopWatch=new StopWatch();
        stopWatch.start();
        List<Map<String,Object>> movies=jdbcTemplate.queryForList(sql,combineVo.getDirector(),combineVo.getYear(),combineVo.getType());
        stopWatch.stop();
        MovieVo movieVo=new MovieVo();
        movieVo.setMovies(movies);
        movieVo.setTimeCost(stopWatch.getLastTaskTimeMillis());
        return movieVo;
    }

    @Override
    public MovieVo getByPopular(){
        return null;
    }
}
