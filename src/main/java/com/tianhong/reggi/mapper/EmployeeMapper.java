package com.tianhong.reggi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianhong.reggi.Pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
