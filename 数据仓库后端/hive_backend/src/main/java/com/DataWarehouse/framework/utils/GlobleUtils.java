package com.DataWarehouse.framework.utils;

/**
 * @Author: Cai MinXing
 * @Create: 2020-04-14 09:51
 **/
public class GlobleUtils {

    // TODO 暂时写死，该变量应由session中获取，使用LocalThread做到session anywhere
    public static String userName = "创建人";

    public static String getUserName() {
        return userName;
    }
}
