package com.glint.disttransaction;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class SchemaInitializer {
    private final DataSource paymentDataSource;
    private final DataSource inventoryDataSource;

    public SchemaInitializer(@Qualifier("paymentDataSource") DataSource paymentDataSource, @Qualifier("inventoryDataSource") DataSource inventoryDataSource) {
        this.paymentDataSource = paymentDataSource;
        this.inventoryDataSource = inventoryDataSource;
    }

    @PostConstruct
    public void init() throws Exception {
        createPaymentSchema();
        createInventorySchema();
    }

    private void createPaymentSchema() throws Exception {
        try (Connection c = paymentDataSource.getConnection();
             Statement s = c.createStatement()) {

            s.execute("""
                        CREATE TABLE IF NOT EXISTS PAYMENTS (
                            STATUS VARCHAR(20)
                        )
                    """);
        }
    }

    private void createInventorySchema() throws Exception {
        try (Connection c = inventoryDataSource.getConnection();
             Statement s = c.createStatement()) {

            s.execute("""
                        CREATE TABLE IF NOT EXISTS INVENTORY (
                            STATUS VARCHAR(20)
                        )
                    """);
        }
    }
}
