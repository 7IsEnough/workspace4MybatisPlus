package com.clearlove.mp.test;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.clearlove.mp.beans.Employee;
import com.clearlove.mp.mapper.EmployeeMapper;
import com.mchange.v1.identicator.IdList;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;

public class TestMP {

    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");


    //AR 插入操作
    @Test
    public void testARInsert(){
        Employee employee = new Employee();
        employee.setLastName("简自豪");
        employee.setEmail("kxg@uzi.com");
        employee.setGender(1);
        employee.setAge(35);

        boolean result = employee.insert();
        System.out.println("result："+result);
    }

    //AR 修改操作
    @Test
    public void testARUpdate(){
        Employee employee = new Employee();
        employee.setId(13);
        employee.setLastName("UZI");
        employee.setEmail("kxg@jzh.com");
        employee.setGender(1);
        employee.setAge(23);

        boolean result = employee.updateById();
        System.out.println("result："+result);
    }

    //AR 查询操作
    @Test
    public void testARSelect(){
        Employee employee = new Employee();
        //Employee result = employee.selectById(13);
//        employee.setId(13);
//        Employee result = employee.selectById();
//        System.out.println(result);

//        List<Employee> emps = employee.selectAll();
//        System.out.println(emps);

//        List<Employee> emps = employee.selectList(new QueryWrapper<Employee>()
//                .like("last_name", "M")
//        );
//        System.out.println(emps);

        Integer result = employee.selectCount(new QueryWrapper<Employee>()
                .eq("gender", 0)
        );

        System.out.println("result："+result);


    }

    //AR 删除操作
    @Test
    public void testARDelete(){
        Employee employee = new Employee();
//        boolean result = employee.deleteById(2);
//        employee.setId(2);
//        boolean result = employee.deleteById();
//        System.out.println("result："+result);

        boolean result = employee.delete(new QueryWrapper<Employee>()
                .like("last_name", "明")
        );
        System.out.println("result："+result);
    }

    //AR 分页复杂操作
    @Test
    public void testARPage(){
        Employee employee = new Employee();
        IPage<Employee> page = employee.selectPage(new Page<Employee>(1, 1),
                new QueryWrapper<Employee>()
                        .like("last_name", "U"));
        List<Employee> emps = page.getRecords();
        System.out.println(emps);
    }


}
