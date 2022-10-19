package com.tianhong.reggi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianhong.reggi.Pojo.Dish;
import com.tianhong.reggi.mapper.DishMapper;
import com.tianhong.reggi.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService{
}
