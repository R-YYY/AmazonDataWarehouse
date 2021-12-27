package com.DataWarehouse.sys.service;

import com.DataWarehouse.sys.entity.Direct;
import com.DataWarehouse.sys.vo.DirectorVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LIAN
 * @since 2021-12-21
 */
public interface DirectService extends IService<Direct> {

    DirectorVo getByTitle(String title);
}
