package com.seedollar.sandbox.springcore.repository;

import com.seedollar.sandbox.springcore.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class MemberRepository {

    private JdbcTemplate jdbcTemplate;

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
}
