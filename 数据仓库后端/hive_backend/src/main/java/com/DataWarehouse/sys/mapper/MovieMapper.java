package com.DataWarehouse.sys.mapper;

import com.DataWarehouse.sys.entity.Movie;
import com.DataWarehouse.sys.vo.ScoreVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LIAN
 * @since 2021-12-21
 */
public interface MovieMapper extends BaseMapper<Movie> {
}
