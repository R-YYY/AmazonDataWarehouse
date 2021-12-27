package com.DataWarehouse.sys.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MovieVo {

    private List<Map<String,Object>> movies;

    private Long timeCost;
}
