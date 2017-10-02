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

    private static RowMapper<Weapon> weaponRowMapper = (rs, rowNum) -> {
        Weapon weapon = new Weapon();
        weapon.setId(rs.getLong(1));
        weapon.setName(rs.getString(2));
        weapon.setDescription(rs.getString(3));
        weapon.setDamageIndicator(rs.getInt(4));
        weapon.setPrice(rs.getFloat(5));
        weapon.setInStock(rs.getBoolean(6));
        return weapon;
    };

    public WeaponRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Weapon> getAll() {
        return jdbcTemplate.query("SELECT id, name, description, damage_indicator, price, in_stock from weapon", weaponRowMapper);
    }

    public List<Weapon> getDiscounted(Float amount) {
        return jdbcTemplate.query("SELECT id, name, description, damage_indicator, price, in_stock from weapon WHERE price <= ?",
                weaponRowMapper,
                amount);
    }

    public List<Weapon> getByInStock(boolean inStock) {
        return jdbcTemplate.query("SELECT id, name, description, damage_indicator, price, in_stock FROM weapon where in_stock = ?",
                weaponRowMapper, inStock);
    }

    public Weapon getItemForId(Long itemId) {
        return jdbcTemplate.queryForObject("SELECT id, name, description, damage_indicator, price, in_stock FROM weapon where id = ?",
                weaponRowMapper, itemId);
    }
}
