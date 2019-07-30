package com.iresearch.svc.datasource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

//表示这个类为一个配置类
@Configuration
// 配置mybatis的接口类放的地方
@MapperScan(basePackages = "com.iresearch.svc.mapper.vf", sqlSessionFactoryRef = "vfSqlSessionFactory")
public class DataSourceConfig2 {
    // 将这个对象放入Spring容器中
    @Bean(name = "vfDataSource")
    // 表示这个数据源是默认数据源
    // prefix表示参数的前缀
    @ConfigurationProperties(prefix = "spring.datasource.vf")
    public DataSource getDateSource1() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "vfSqlSessionFactory")
    // @Qualifier表示查找Spring容器中名字为vfDataSource的对象
    public SqlSessionFactory vfSqlSessionFactory(@Qualifier("vfDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/vf/*.xml"));
        return bean.getObject();
    }
    @Bean("vfSqlSessionTemplate")
    public SqlSessionTemplate vfsqlsessiontemplate(
            @Qualifier("vfSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}