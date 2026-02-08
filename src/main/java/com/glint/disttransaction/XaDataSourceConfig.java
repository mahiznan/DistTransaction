package com.glint.disttransaction;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class XaDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.payment.xa")
    public DataSource paymentDataSource() {
        return new AtomikosDataSourceBean();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.inventory.xa")
    public DataSource inventoryDataSource() {
        return new AtomikosDataSourceBean();
    }
}
