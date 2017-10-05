package com.seedollar.sandbox.springcore.repository;

import com.seedollar.sandbox.springcore.domain.Member;
import io.vavr.Function6;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class MemberRepository {

    private JdbcTemplate jdbcTemplate;

    private static RowMapper<Member> rowMapper = (rs, rowNum) -> {
        Member member = new Member();
        member.setId(rs.getLong(1));
        member.setUsername(rs.getString(2));
        member.setEmail(rs.getString(3));
        member.setPassword(rs.getString(4));
        member.setRegistrationDate(rs.getDate(5));
        member.setActive(rs.getBoolean(6));
        return member;
    };

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long add(Member member) {
        Long newId = ThreadLocalRandom.current().nextLong();
        jdbcTemplate.update("INSERT INTO member (id, username, password, email, registration_date, active) VALUES (?,?,?,?,?,?);",
                newId, member.getUsername(), member.getPassword(), member.getEmail(), LocalDateTime.now(), true);
        return newId;
    }

    public boolean remove(Long memberId) {
        jdbcTemplate.update("DELETE FROM member where id = ?", memberId);
        return true;
    }

    public Member findByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT id, username, password, email, registration_date, active FROM member WHERE username = ?", rowMapper, username);
    }
}
