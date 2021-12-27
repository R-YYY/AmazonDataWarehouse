package com.DataWarehouse.framework.enums;

/**
 * 所有异常状态码均需实现此接口
 */
public interface StatusCode {
    public int getCode();

    public String getMsg();
}
