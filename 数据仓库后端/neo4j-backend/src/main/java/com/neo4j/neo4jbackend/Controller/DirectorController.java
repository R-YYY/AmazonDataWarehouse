package com.neo4j.neo4jbackend.Controller;

import com.neo4j.neo4jbackend.Service.DirectorService;
import com.neo4j.neo4jbackend.Vo.DirectorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direct")
public class DirectorController {

    @Autowired
    DirectorService directorService;

    @GetMapping("/getbytitle")
    public DirectorVo getByTitle(String title){
        return directorService.getByTitle(title);
    }

    @GetMapping("/getbyactor")
    public DirectorVo getByActor(String actor){
        return directorService.getByActor(actor);
    }
}
