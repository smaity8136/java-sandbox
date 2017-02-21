package com.seedollar.java.spring.jwt.security;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import javaslang.control.Try;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by seedollar on 2/21/17.
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private static Map<String, User> cachedUsers = Maps.newHashMap();

    static {
        cachedUsers.put("admin", new User("admin", "password", true, false, false, false, Lists.newArrayList()));
        cachedUsers.put("oliver", new User("oliver", "password", true, false, false, false, Lists.newArrayList()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Try.of(() -> cachedUsers.get(username)).getOrElseThrow(() -> new UsernameNotFoundException(String.format("User [%s] does not exist.", username)));
    }
}
