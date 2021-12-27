package com.DataWarehouse.sys.vo;

import com.DataWarehouse.sys.entity.Act;
import lombok.Data;

import java.util.List;

@Data
public class ActorVo {

    private List<Act> actors;

    private Long timeCost;
}
