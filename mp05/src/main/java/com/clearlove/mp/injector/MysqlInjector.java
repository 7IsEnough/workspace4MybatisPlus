package com.clearlove.mp.injector;

import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.injector.SqlRunnerInjector;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.session.Configuration;

/***
 * 自定义全局操作
 */
public class MysqlInjector extends AbstractSqlInjector {

    //扩展inject方法，完成自定义全局操作
    //将EmployeeMapper中定义的deleteAll，处理成对应的MappedStatement对象，加入到configuration对象中

    @Override
    public void inspectInject(MapperBuilderAssistant builderAssistant, Class<?> mapperClass) {

    }
}
