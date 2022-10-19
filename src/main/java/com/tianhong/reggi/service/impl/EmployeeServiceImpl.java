package com.tianhong.reggi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianhong.reggi.Pojo.Employee;
import com.tianhong.reggi.mapper.EmployeeMapper;
import com.tianhong.reggi.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{
}
