package com.wx.watersupplierservice.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.xdf.pscommon.aop.DataSourceType;
import com.xdf.pscommon.aop.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库配置 weili
 */
@Configuration
@MapperScan(basePackages="com.wx.watersupplierservice.dao")
public class DataSourceConfig {

    private Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Value("${mybatis.configLocation}")
    private String configLocation;

    @Autowired
    WallFilter wallFilter;

    @Autowired
    StatFilter statFilter;

    @Autowired
    Slf4jLogFilter slf4jLogFilter;

    //  加载全局的配置文件
//    @Value("${mybatis.configLocation}")
//    private String configLocation;

    /**
     * 写库 数据源配置
     * @return
     */
    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "defaultsql.datasource")
    public DataSource writeDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        List<Filter> filters = new ArrayList<>();
        //从写wallFilter 开启批量执行
        filters.add(wallFilter);
        filters.add(statFilter);
        filters.add(slf4jLogFilter);
        dataSource.setProxyFilters(filters);
        return dataSource;
    }

    /**
     * 从库配置
     * @return
     */
    @Bean(name = "slave1DataSource")
    @ConfigurationProperties(prefix = "defaultsql.slave1.datasource")
    public DataSource readDataSourceOne() {
        log.info("-------------------- slave1 DataSourceOne init ---------------------");
        DruidDataSource dataSource = new DruidDataSource();

        return dataSource;
    }

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactorys(@Qualifier("dataSource") DataSource masterDataSource,
                                                @Qualifier("slave1DataSource") DataSource slave1DataSource) throws Exception {
        log.info("--------------------  sqlSessionFactory init --------------------- {} {} {}",
                ((DruidDataSource)masterDataSource).getRawJdbcUrl(),((DruidDataSource)slave1DataSource).getRawJdbcUrl());
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(roundRobinDataSouceProxy(masterDataSource,slave1DataSource));

            //设置mapper.xml文件所在位置
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
            sessionFactoryBean.setMapperLocations(resources);
            //设置mybatis-config.xml配置文件位置
            sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));

            return SqlSessionManager.newInstance(sessionFactoryBean.getObject());
        } catch (IOException e) {
            log.error("mybatis resolver mapper*xml is error",e);
            return null;
        } catch (Exception e) {
            log.error("mybatis sqlSessionFactoryBean create error",e);
            return null;
        }
    }

    /**
     * 把所有数据库都放在路由中
     * @return
     */
    @Bean(name="roundRobinDataSouceProxy")
    public DynamicDataSource roundRobinDataSouceProxy(@Qualifier("dataSource") DataSource masterDataSource,
                                                      @Qualifier("slave1DataSource") DataSource slave1DataSource) {

        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //把所有数据库都放在targetDataSources中,注意key值要和determineCurrentLookupKey()中代码写的一至，
        //否则切换数据源时找不到正确的数据源
        targetDataSources.put(DataSourceType.MASTER.getCode(), masterDataSource);
        targetDataSources.put(DataSourceType.SLAVE.getCode()+"1", slave1DataSource);
        //路由类，寻找对应的数据源
        DynamicDataSource proxy = new DynamicDataSource();
        proxy.setDefaultTargetDataSource(masterDataSource);//默认库
        proxy.setTargetDataSources(targetDataSources);
        proxy.afterPropertiesSet();
        return proxy;
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean("platformTransactionManager")
    @Primary
    public PlatformTransactionManager annotationDrivenTransactionManager(@Qualifier("roundRobinDataSouceProxy") DynamicDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    public WallFilter wallFilter(){
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }

    @Bean
    public WallConfig wallConfig(){
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);
        return config;
    }

    @Bean
    public StatFilter statFilter(){
        return new StatFilter();
    }

    @Bean
    public Slf4jLogFilter slf4jLogFilter(){
        return new Slf4jLogFilter();
    }

}
