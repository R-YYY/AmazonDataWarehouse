package com.DataWarehouse.sys.controller;


import com.DataWarehouse.sys.service.MovieService;
import com.DataWarehouse.sys.vo.CombineVo;
import com.DataWarehouse.sys.vo.DateVo;
import com.DataWarehouse.sys.vo.MovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author LIAN
 * @since 2021-12-21
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/getbydate")
    public MovieVo getByDate(@Validated @RequestBody DateVo dateVo)
    {
        return movieService.getByDate(dateVo);
    }

    @GetMapping("/getbytitle")
    public MovieVo getByTitle(@RequestParam String title)
    {
        System.out.println(title);
        return movieService.getByTitle(title);
    }

    @GetMapping("/getbydirector")
    public MovieVo getByDirector(@RequestParam String name){
        return movieService.getByDirector(name);
    }

    @GetMapping("/getbyactor")
    public MovieVo getByActor(@RequestParam String name){
        return movieService.getByActor(name);
    }

    @GetMapping("/getbyscore")
    public MovieVo getByScore(@RequestParam double score){
        return movieService.getByScore(score);
    }

    @GetMapping("/getbytype")
    public MovieVo getByType(@RequestParam String type){
        return movieService.getByType(type);
    }

    @GetMapping("/getbypopular")
    public MovieVo getByPopular(){
        return movieService.getByPopular();
    }

    @PostMapping("/getbycombine")
    public MovieVo getByCombine(@RequestBody CombineVo combineVo){
        return movieService.getByCombine(combineVo);
    }
}
