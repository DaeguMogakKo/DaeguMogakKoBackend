package com.mgk.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.mgk.mapper", sqlSessionFactoryRef = "sqlSessionFactoryBean")

public class MybatisConfig {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Primary
    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:/com/mgk/mapper/*Mapper.xml");
        sessionFactory.setMapperLocations(res);
        return sessionFactory.getObject();
    }

}
