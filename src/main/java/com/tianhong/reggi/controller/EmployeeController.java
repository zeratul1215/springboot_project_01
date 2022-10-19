package com.tianhong.reggi.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianhong.reggi.Pojo.Employee;
import com.tianhong.reggi.common.result.Result;
import com.tianhong.reggi.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Result<Employee> login(HttpServletRequest request, @RequestBody Employee employee){
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<Employee>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        if(emp == null){
            return Result.error("login failed, no such username");
        }
        if(!emp.getPassword().equals(password)){
            return Result.error("login failed, wrong password");
        }
        if(emp.getStatus() == 0){
            return Result.error("account banned");
        }
        //login success
        request.getSession().setAttribute("employee",emp.getId());
        return Result.success(emp);
    }


    @PostMapping("/logout")
    public Result<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return Result.success("logout success");
    }

    @PostMapping
    public Result<String> save(HttpServletRequest request, @RequestBody Employee employee){

        log.info("new Employee:{}",employee.toString());
        //initial password (md5) as 123456
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        //who created this user
        //long id = (long) request.getSession().getAttribute("employee");
        //employee.setCreateUser(id);
        //employee.setUpdateUser(id);
        //单独捕获不推荐 用全局异常捕获
        /*try {
            employeeService.save(employee);
        }
        catch (Exception ex){
            return Result.error("Employee already exists");
        }*/
        employeeService.save(employee);
        return Result.success("Add success");
    }

    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize,String name){
        log.info("page = {}, pageSize = {}, name = {}", page,pageSize,name);
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //selecting
        queryWrapper.like(!StringUtils.isEmpty(name),Employee::getName,name);
        //sorting
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,queryWrapper);
        return Result.success(pageInfo);
    }

    @PutMapping
    public Result<String> update(HttpServletRequest request, @RequestBody Employee employee){
        log.info(employee.toString());
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser((long)request.getSession().getAttribute("employee"));
        employeeService.updateById(employee);
        return Result.success("changed success");
    }

    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable long id){
        log.info(""+id);
        Employee byId = employeeService.getById(id);
        if(byId != null){
            return Result.success(byId);
        }
        return Result.error("no such employee");
    }
}
