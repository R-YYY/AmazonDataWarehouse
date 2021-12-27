package com.DataWarehouse.sys.vo;

import com.DataWarehouse.sys.entity.Direct;
import lombok.Data;

import java.util.List;

@Data
public class DirectorVo {

    private List<Direct> directors;

    private  Long timeCost;
}
