package com.tianhong.reggi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianhong.reggi.Pojo.Setmeal;
import com.tianhong.reggi.mapper.SetMealMapper;
import com.tianhong.reggi.service.SetMealService;
import org.springframework.stereotype.Service;

@Service
public class SetMealServceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetMealService {
}
