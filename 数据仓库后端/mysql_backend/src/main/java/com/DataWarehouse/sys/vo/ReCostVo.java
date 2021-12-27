package com.DataWarehouse.sys.vo;

import lombok.Data;

import java.util.List;

@Data
public class ReCostVo {

    private List<RelationVo> relations;

    private Long timeCost;
}
