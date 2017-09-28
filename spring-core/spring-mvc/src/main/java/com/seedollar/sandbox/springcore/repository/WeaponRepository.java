package com.seedollar.sandbox.springcore.repository;

import com.seedollar.sandbox.springcore.domain.Weapon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WeaponRepository {


    private JdbcTemplate jdbcTemplate;

    public WeaponRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Weapon> getAll() {
        return jdbcTemplate.query("SELECT id, name, description, damage_indicator, price, in_stock from weapon", (rs, rowNum) -> {
            Weapon weapon = new Weapon();
            weapon.setId(rs.getLong(0));
            weapon.setName(rs.getString(1));
            weapon.setDescription(rs.getString(2));
            weapon.setDamageIndicator(rs.getInt(3));
            weapon.setPrice(rs.getFloat(4));
            weapon.setInStock(rs.getBoolean(5));
            return weapon;
        });
    }
}
