package com.DataWarehouse.sys.mapper;

import com.DataWarehouse.sys.entity.Movie;
import com.DataWarehouse.sys.vo.RelationVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LIAN
 * @since 2021-12-21
 */
public interface MovieMapper extends BaseMapper<Movie> {

    @Results(
            id="getByDirector",
            value = {
                    @Result(property = "movieId",column = "movie_id"),
                    @Result(property = "title",column = "title"),
                    @Result(property = "directorNum",column = "director_num"),
                    @Result(property = "actorNum",column = "actor_num"),
                    @Result(property = "score",column = "score"),
                    @Result(property = "format",column = "format"),
                    @Result(property = "type",column = "type"),
                    @Result(property = "year",column = "year"),
                    @Result(property = "month",column = "month"),
                    @Result(property = "day",column = "day"),
                    @Result(property = "reviewNum",column = "review_num"),
            }
    )
    @Select("SELECT * FROM movie,direct where director_name=#{name} AND direct.movie_id=movie.movie_id")
    List<Movie> getByDirector(@Param("name") String name);

    @Results(
            id="getByActor",
            value = {
                    @Result(property = "movieId",column = "movie_id"),
                    @Result(property = "title",column = "title"),
                    @Result(property = "directorNum",column = "director_num"),
                    @Result(property = "actorNum",column = "actor_num"),
                    @Result(property = "score",column = "score"),
                    @Result(property = "format",column = "format"),
                    @Result(property = "type",column = "type"),
                    @Result(property = "year",column = "year"),
                    @Result(property = "month",column = "month"),
                    @Result(property = "day",column = "day"),
                    @Result(property = "reviewNum",column = "review_num"),
            }
    )
    @Select("SELECT * FROM movie,act where actor_name=#{name} AND act.movie_id=movie.movie_id")
    List<Movie> getByActor(@Param("name") String name);

    @Select("SELECT * FROM movie ORDER BY review_num DESC LIMIT 100")
    List<Movie> getByPopular();

    @Results(
            id="getByRelation",
            value = {
                    @Result(property = "directorName",column = "director_name"),
                    @Result(property = "actorName",column = "actor_name"),
                    @Result(property = "times",column = "times"),
            }
    )
    @Select("SELECT * FROM " +
            "( SELECT director_name, actor_name, count( movie_id ) AS times" +
            " FROM direct NATURAL JOIN act GROUP BY director_name, actor_name ) AS tmp" +
            " WHERE times >= #{minTimes}")
    List<RelationVo> getByRelation(Integer minTimes);

    @Results(
            id="getByDirectorRelation",
            value = {
                    @Result(property = "directorName",column = "director_name"),
                    @Result(property = "actorName",column = "actor_name"),
                    @Result(property = "times",column = "times"),
            }
    )
    @Select("SELECT * FROM " +
            "( SELECT director_name, actor_name, count( movie_id ) AS times" +
            " FROM (SELECT * FROM direct WHERE director_name = #{directorName}) as a NATURAL JOIN act GROUP BY actor_name ) AS tmp" +
            " WHERE times >= #{minTimes}")
    List<RelationVo> getByDirectorRelation(String directorName,Integer minTimes);

    @Results(
            id="getByActorRelation",
            value = {
                    @Result(property = "directorName",column = "director_name"),
                    @Result(property = "actorName",column = "actor_name"),
                    @Result(property = "times",column = "times"),
            }
    )
    @Select("SELECT * FROM " +
            "( SELECT director_name, actor_name, count( movie_id ) AS times" +
            " FROM (SELECT * FROM act WHERE actor_name = #{actorName} ) as a NATURAL JOIN direct GROUP BY director_name) AS tmp" +
            " WHERE times >= #{minTimes}")
    List<RelationVo> getByActorRelation(String actorName, Integer minTimes);

    @Results(
            id="getByCombine",
            value = {
                    @Result(property = "movieId",column = "movie_id"),
                    @Result(property = "title",column = "title"),
                    @Result(property = "directorNum",column = "director_num"),
                    @Result(property = "actorNum",column = "actor_num"),
                    @Result(property = "score",column = "score"),
                    @Result(property = "format",column = "format"),
                    @Result(property = "type",column = "type"),
                    @Result(property = "year",column = "year"),
                    @Result(property = "month",column = "month"),
                    @Result(property = "day",column = "day"),
                    @Result(property = "reviewNum",column = "review_num"),
            }
    )
    @Select("SELECT * FROM movie,direct where director_name=#{director} AND direct.movie_id=movie.movie_id AND movie.year=#{year} AND movie.type=#{type}")
    List<Movie> getByCombine(@Param("year") String year,@Param("director") String director,@Param("type") String type);
}
