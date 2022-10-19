package com.tianhong.reggi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianhong.reggi.Pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
