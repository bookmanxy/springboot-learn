package cn.faceland.springbootdataserver.config;

import cn.faceland.springbootdataserver.common.DataConstants;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @description 数据源的配置
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = DataConstants.DATASOURCE_BEAN_NAME)
    @ConfigurationProperties(prefix = DataConstants.DATASOURCE_CONFIGURATION_PREFIX)
    public DataSource biu() {
        return DruidDataSourceBuilder.create().build();
    }
}