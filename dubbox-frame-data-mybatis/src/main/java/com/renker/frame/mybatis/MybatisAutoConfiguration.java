package com.renker.frame.mybatis;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ConditionalOnClass(SqlSessionFactoryBean.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@MapperScan(basePackages = {"**/mapper", "**/mappers", "**/dao"})
@EnableTransactionManagement
public class MybatisAutoConfiguration {
	@Configuration
    @ConditionalOnMissingBean(SqlSessionFactory.class)
    protected static class SqlSessionFactoryConfiguration {

        @Bean
        @Autowired
        public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            return sessionFactory.getObject();
        }
    }
}
