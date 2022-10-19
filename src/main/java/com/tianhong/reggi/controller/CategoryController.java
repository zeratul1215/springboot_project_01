package com.tianhong.reggi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianhong.reggi.Pojo.Category;
import com.tianhong.reggi.common.result.Result;
import com.tianhong.reggi.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    private Result<String> save(@RequestBody Category category){
        categoryService.save(category);
        return Result.success("add success");
    }

    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize){
        log.info("page = {},pageSize = {}, name = {}",page,pageSize);
        Page pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    @DeleteMapping
    public Result<String> delete(Long ids){
        log.info("deleit id: {}",ids);
        categoryService.remove(ids);
        return Result.success("category deleted");
    }

    @PutMapping
    public Result<String> update(@RequestBody Category category){
        categoryService.updateById(category);
        return Result.success("updated successful");
    }
}
