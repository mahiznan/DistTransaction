package com.glint.disttransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepository {


    private final JdbcTemplate jdbcTemplate;

    public InventoryRepository(@Qualifier("inventoryJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void reserve() {
        jdbcTemplate.update("INSERT INTO inventory VALUES ('reserved')");
    }
}

