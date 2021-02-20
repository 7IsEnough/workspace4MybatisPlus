package com.clearlove.mp.test;


import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clearlove.mp.beans.Employee;
import com.clearlove.mp.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestMP {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

    EmployeeMapper employeeMapper = ctx.getBean("employeeMapper",EmployeeMapper.class);


    //测试分页插件
    @Test
    public void testPage(){
        Page<Employee> page = new Page<>(1,1);

        Page<Employee> ipage = employeeMapper.selectPage(page, null);
        List<Employee> emps = ipage.getRecords();
        System.out.println(emps);
        System.out.println("==========获取分页相关信息========");
        System.out.println("总条数："+ipage.getTotal());
        System.out.println("当前页码："+ipage.getCurrent());
        System.out.println("总页码："+ipage.getPages());
        System.out.println("每页显示的条数："+ipage.getSize());
        System.out.println("是否有上一页："+ipage.hasPrevious());
        System.out.println("是否有下一页："+ipage.hasNext());

    }

    //测试SQL执行分析插件
    @Test
    public void testSQLExplain(){
        employeeMapper.delete(null);  //全表删除
    }

    //测试乐观锁插件
    @Test
    public void testOptimisticLocker(){
        //更新操作
        Employee employee = new Employee();
        employee.setId(1);
        employee.setLastName("Clearlove7");
        employee.setEmail("mk@clearlove.com");
        employee.setGender("1");
        employee.setAge(22);
        employee.setVersion(3);


        employeeMapper.updateById(employee);



    }


}
