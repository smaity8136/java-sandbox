package com.seedollar.java.spring.integration.service;

import java.util.List;

/**
 * Created by seedollar on 9/27/16.
 */
public interface ItemService<T> {

    void execute(List<T> elements);
}
