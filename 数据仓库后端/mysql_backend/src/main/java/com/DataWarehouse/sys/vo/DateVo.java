package com.DataWarehouse.sys.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DateVo {

    @NotEmpty(message = "年份不能为空")
    private String year;

    private String month;

    private String day;

    private Integer season;
}
