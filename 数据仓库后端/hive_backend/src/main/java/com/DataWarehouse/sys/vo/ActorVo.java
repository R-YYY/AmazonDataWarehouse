package com.DataWarehouse.sys.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ActorVo {

    private List<Map<String,Object>> actors;

    private Long timeCost;
}
