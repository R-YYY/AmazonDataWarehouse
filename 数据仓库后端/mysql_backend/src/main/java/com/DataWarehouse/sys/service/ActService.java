package com.DataWarehouse.sys.service;

import com.DataWarehouse.sys.entity.Act;
import com.DataWarehouse.sys.vo.ActorVo;
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
public interface ActService extends IService<Act> {

    ActorVo getByTitle(String title);

    ActorVo getByTitle2(String title);
}
