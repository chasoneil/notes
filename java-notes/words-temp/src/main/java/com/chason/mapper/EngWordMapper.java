package com.chason.mapper;

import com.chason.entity.english.EngWords;

import java.util.List;

/**
 * for mybatis
 * data exchange from program to database
 */
public interface EngWordMapper {

    int insert(EngWords engWords);

    List<EngWords> listAll();
}
