package com.glint.disttransaction;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class XaDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "datasource.payment.xa")
    public DataSource paymentDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("payment-xa-db");
        return getDataSource(ds);
    }

    @Bean
    @ConfigurationProperties(prefix = "datasource.inventory.xa")
    public DataSource inventoryDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("inventory-xa-db");
        return getDataSource(ds);
    }

    @NonNull
    private DataSource getDataSource(AtomikosDataSourceBean ds) {
        ds.setXaDataSourceClassName("org.h2.jdbcx.JdbcDataSource");

        Properties props = new Properties();
        props.setProperty("URL", "jdbc:h2:mem:paymentdb;DB_CLOSE_DELAY=-1");
        props.setProperty("user", "sa");
        props.setProperty("password", "");

        ds.setXaProperties(props);
        return ds;
    }

    @Bean
    public JdbcTemplate paymentJdbcTemplate(
            @Qualifier("paymentDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    public JdbcTemplate inventoryJdbcTemplate(
            @Qualifier("inventoryDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
