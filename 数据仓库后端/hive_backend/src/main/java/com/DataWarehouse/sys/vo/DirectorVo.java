package com.DataWarehouse.sys.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DirectorVo {

    private List<Map<String,Object>> directors;

    private  Long timeCost;
}
