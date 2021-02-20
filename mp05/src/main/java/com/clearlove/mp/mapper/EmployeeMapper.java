package com.clearlove.mp.mapper;

import com.clearlove.mp.beans.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Clearlove
 * @since 2020-08-14
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
        int deleteAll();
}
