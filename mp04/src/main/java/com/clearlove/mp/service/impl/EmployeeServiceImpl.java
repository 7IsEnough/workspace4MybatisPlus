package com.clearlove.mp.service.impl;

import com.clearlove.mp.beans.Employee;
import com.clearlove.mp.mapper.EmployeeMapper;
import com.clearlove.mp.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Clearlove
 * @since 2020-08-14
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
