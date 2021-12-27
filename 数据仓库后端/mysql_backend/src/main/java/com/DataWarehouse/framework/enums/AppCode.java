package com.DataWarehouse.framework.enums;

import lombok.Getter;

/**
 * 业务异常
 */
@Getter
public enum AppCode implements StatusCode {

    APP_ERROR(2000, "业务异常"),
    ;

    private int code;
    private String msg;

    AppCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
