package com.DataWarehouse.sys.controller;


import com.DataWarehouse.sys.service.ActService;
import com.DataWarehouse.sys.service.DirectService;
import com.DataWarehouse.sys.vo.DirectorVo;
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
@RequestMapping("/direct")
public class DirectController {

    @Autowired
    DirectService directService;

    @GetMapping("/getbytitle")
    public DirectorVo getByTitle(@RequestParam String title){
        return directService.getByTitle(title);
    }

    @GetMapping("/getbytitlenew")
    public DirectorVo getByTitleNew(@RequestParam String title){
        return directService.getByTitle2(title);
    }
}
