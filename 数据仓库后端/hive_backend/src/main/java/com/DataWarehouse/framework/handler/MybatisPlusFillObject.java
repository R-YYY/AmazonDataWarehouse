package com.DataWarehouse.framework.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.DataWarehouse.framework.utils.GlobleUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: Cai MinXing
 * @Create: 2020-04-14 09:49
 **/
@Component
public class MybatisPlusFillObject implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createUser", String.class, GlobleUtils.getUserName());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateUser", String.class, GlobleUtils.getUserName());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateUser", String.class, GlobleUtils.getUserName());
    }
}
