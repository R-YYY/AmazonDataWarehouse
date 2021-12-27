package com.DataWarehouse.sys.service;

import com.DataWarehouse.sys.entity.Movie;
import com.DataWarehouse.sys.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LIAN
 * @since 2021-12-21
 */
public interface MovieService extends IService<Movie> {

    MovieVo getByDate(DateVo dateVo);

    MovieVo getByTitle(String title);

    MovieVo getByDirector(String name);

    MovieVo getByActor(String name);

    MovieVo getByScore(double score);

    MovieVo getByType(String type);

    MovieVo getByPopular();

    ReCostVo getByRelation(RelationVo relationVo);

    MovieVo getByCombine(CombineVo combineVo);
}
