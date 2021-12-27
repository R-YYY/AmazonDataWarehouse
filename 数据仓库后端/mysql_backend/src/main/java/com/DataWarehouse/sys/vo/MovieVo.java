package com.DataWarehouse.sys.vo;

import com.DataWarehouse.sys.entity.Movie;
import lombok.Data;

import java.util.List;

@Data
public class MovieVo {

    private List<Movie> movies;

    private Long timeCost;
}
