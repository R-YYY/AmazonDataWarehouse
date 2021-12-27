package com.neo4j.neo4jbackend.dao;

import com.neo4j.neo4jbackend.entity.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie,Long> {

    @Query("MATCH(n:movie) WHERE n.title={title} return n")
    Collection<Movie> getByTitle(@Param("title") String title);

    @Query("MATCH(n:movie) WHERE n.score>={score} return n")
    Collection<Movie> getByScore(@Param("score") String score);

    @Query("MATCH(n:movie) WHERE n.type={type} return n")
    Collection<Movie> getByType(@Param("type") String type);

    @Query("MATCH(n:movie) WHERE n.review_num<>'null' return n ORDER BY n.review_num DESC LIMIT 100")
    Collection<Movie> getByPopular();

    @Query("MATCH (x:actor)-[r:ACT]->(y:movie) WHERE x.name={actor} return y")
    Collection<Movie> getByActor(@Param("actor") String actor);

    @Query("MATCH (x:director)-[r:DIRECT]->(y:movie) WHERE x.name={director} return y")
    Collection<Movie> getByDirector(@Param("director") String director);

    @Query("MATCH (n:movie) WHERE n.year={year} AND n.month>='1' AND n.month<='3' return n")
    Collection<Movie> getBySeason1(@Param("year") String year);

    @Query("MATCH (n:movie) WHERE n.year={year} AND n.month>='4' AND n.month<='6' return n")
    Collection<Movie> getBySeason2(@Param("year") String year);

    @Query("MATCH (n:movie) WHERE n.year={year} AND n.month>='7' AND n.month<='9' return n")
    Collection<Movie> getBySeason3(@Param("year") String year);

    @Query("MATCH (n:movie) WHERE n.year={year} AND n.month>='10' AND n.month<='12' return n")
    Collection<Movie> getBySeason4(@Param("year") String year);

    @Query("MATCH (n:movie) WHERE n.year={year} AND n.month={month} AND n.day={day} return n")
    Collection<Movie> getByDate(@Param("year") String year,@Param("month") String month,@Param("day") String day);

    @Query("MATCH (n:movie) WHERE n.year={year} return n")
    Collection<Movie> getByDate1(@Param("year") String year);

    @Query("MATCH (n:movie) WHERE n.year={year} AND n.month={month} return n")
    Collection<Movie> getByDate2(@Param("year") String year,@Param("month") String month);

    @Query("MATCH (x:director)-[r:DIRECT]->(y:movie) WHERE x.name={director} AND y.year={year} AND y.type={type} return y")
    Collection<Movie> getByCombine(@Param("year") String year,@Param("director") String director,@Param("type") String type);

}
