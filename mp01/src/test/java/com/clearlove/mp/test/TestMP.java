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

    private EmployeeMapper employeeMapper = ioc.getBean("employeeMapper",EmployeeMapper.class);

    //条件构造器  删除操作
    @Test
    public void testEntityWrapperDeltete(){

        employeeMapper.delete(new QueryWrapper<Employee>()
                .eq("last_name","Tom")
                .eq("age",22)
        );

    }


    //条件构造器  修改操作
    @Test
    public void testEntityWrapperUpdate(){
        Employee employee = new Employee();
        employee.setLastName("明凯");
        employee.setEmail("mk@sina.com");
        employee.setGender(0);

        employeeMapper.update(employee,
                new QueryWrapper<Employee>()
                .eq("last_name","MP")
                .eq("age",44)
        );
    }



    //条件构造器  查询操作
    @Test
    public void testEntityWrapperSelect(){
        //我们需要分页查询tbl_employee表中，年龄在18~50之间且性别为男且姓名为Tom的所有用户

//        List<Employee> emps = employeeMapper.selectPage(new Page<Employee>(1, 2),
//                new EntityWrapper<Employee>()
//                        .between("age", 18, 50)
//                        .eq("gender", 1)
//                        .eq("last_name", "Tom")
//        );
//        System.out.println(emps);

        IPage<Employee> emps = employeeMapper.selectPage(new Page<Employee>(1, 2),
                new QueryWrapper<Employee>()
                        .between("age", 18, 50)
                        .eq("gender", 1)
//                        .eq("last_name", "Tom")

        );
        System.out.println(emps.getRecords());

        //查询tbl_employee表中，性别为女并且名字中带有"明"或者邮箱中带有"a"
//        List<Employee> emps = employeeMapper.selectList(new QueryWrapper<Employee>()
//                .eq("gender", 0)
//                .like("last_name", "明")
//                .or()         //SQL: (gender = ? AND last_name LIKE ? OR email LIKE ?)
//                //.orNew()      //SQL：(gender = ? AND last_name LIKE ?) OR (email LIKE ?)
//                .like("email", "a")
//        );
//
//        System.out.println(emps);

        //查询性别为女，根据age进行排序(asc/desc), 简单分页

//        List<Employee> emps = employeeMapper.selectList(new QueryWrapper<Employee>()
//                .eq("gender", 0)
//                .orderByAsc("age")
//                //.orderByDesc("age")
//                .last("limit 1,3")
//        );
//        System.out.println(emps);


    }


    //通用删除操作
    @Test
    public void testCommonDelete(){
        //1.根据ID进行删除
//        Integer result = employeeMapper.deleteById(5);
//        System.out.println("result："+result);
        //2.根据条件进行删除
//        Map<String,Object> columnMap = new HashMap<>();
//        columnMap.put("last_name","MP");
//        columnMap.put("email","mp@clearlove.com");
//        Integer result = employeeMapper.deleteByMap(columnMap);
//        System.out.println("result："+result);

        //3.批量删除
        List<Integer> idList = new ArrayList<>();
        idList.add(8);
        idList.add(9);
        idList.add(10);
        Integer result = employeeMapper.deleteBatchIds(idList);
        System.out.println("result："+result);

    }

    //通用查询操作
    @Test
    public void testCommonSelect(){

        //1.通过id查询
//        Employee employee = employeeMapper.selectById(7);
//        System.out.println(employee);

        //2.通过多个列进行查询  id + lastName
//        Employee employee = new Employee();
//        employee.setId(7);
//        employee.setLastName("Promise");
//        Employee result = employeeMapper.selectOne(employee);
//        System.out.println("result："+result);

        //3.通过多个id进行查询
//        ArrayList<Integer> idList = new ArrayList<>();
//        idList.add(4);
//        idList.add(5);
//        idList.add(6);
//        idList.add(7);
//        List<Employee> emps = employeeMapper.selectBatchIds(idList);
//        System.out.println(emps);

        //4.通过Map封装条件查询
//        Map<String,Object> columnMap = new HashMap<>();
//        columnMap.put("last_name","明凯");
//        columnMap.put("gender",0);
//
//        List<Employee> emps = employeeMapper.selectByMap(columnMap);
//        System.out.println(emps);

        //5.分页查询
//        List<Employee> emps = employeeMapper.selectPage(new Page<>(3, 2), null);
//        System.out.println(emps);

    }

    //通用更新操作
    @Test
    public void testCommonUpdate(){
        //初始化修改对象
        Employee employee = new Employee();
        employee.setId(7);
        employee.setLastName("Promise");
        employee.setEmail("mk@clearlove.com");
        employee.setGender(0);
       //employee.setAge(33);

       // Integer result = employeeMapper.updateById(employee);
        Integer result = employeeMapper.update(employee,null);
        System.out.println("result: "+result);
    }

    //通用插入操作
    @Test
    public void testCommonInsert(){
        //初始化Employee对象

        Employee employee = new Employee();
        employee.setLastName("MP");
        employee.setEmail("mp@clearlove.com");
//        employee.setGender(1);
//        employee.setAge(22);

//        employee.setSalary(20000.0);
        //插入到数据库
        //insert方法在插入时，会根据实体类的每个属性进行非空判断，只有非空的属性对应的字段才会出现到SQL语句中
//        Integer result = employeeMapper.insert(employee);

        //insertAllColumn方法在插入时，不管属性是否非空，属性所对应的字段都会出现到SQL语句中
        Integer result = employeeMapper.insert(employee);
        System.out.println("result: "+ result);


        //获取当前数据在数据库中的主键值
        Integer key = employee.getId();
        System.out.println("key：" +key);
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource ds = ioc.getBean("dataSource",DataSource.class);
        System.out.println(ds);

        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
