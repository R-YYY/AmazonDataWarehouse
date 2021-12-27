package com.DataWarehouse.sys.controller;


import com.DataWarehouse.sys.service.ActService;
import com.DataWarehouse.sys.vo.ActorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/act")
public class ActController {

    @Autowired
    ActService actService;

    @GetMapping("/getbytitle")
    public ActorVo getByTitle(@RequestParam String title){
        return actService.getByTitle(title);
    }
}
