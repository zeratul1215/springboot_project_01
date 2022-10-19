package com.tianhong.reggi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianhong.reggi.Pojo.Category;
import com.tianhong.reggi.Pojo.Dish;
import com.tianhong.reggi.Pojo.Setmeal;
import com.tianhong.reggi.common.exceptionHandler.CustomException;
import com.tianhong.reggi.mapper.CategoryMapper;
import com.tianhong.reggi.service.CategoryService;
import com.tianhong.reggi.service.DishService;
import com.tianhong.reggi.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    DishService dishService;

    @Autowired
    SetMealService setMealService;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void remove(Long id){
        //look up whether the category is related to the dish/setmeal
        LambdaQueryWrapper<Dish> lwq = new LambdaQueryWrapper<>();
        lwq.eq(Dish::getCategoryId, id);
        int count = dishService.count(lwq);
        if(count > 0){
            throw new CustomException("this catagory is related to some dishes");
        }
        LambdaQueryWrapper<Setmeal> lwq1 = new LambdaQueryWrapper<>();
        lwq1.eq(Setmeal::getCategoryId, id);
        count = setMealService.count(lwq1);
        if (count > 0){
            throw new CustomException("this catagory is related to some set meals");
        }
        super.removeById(id);
    }
}
