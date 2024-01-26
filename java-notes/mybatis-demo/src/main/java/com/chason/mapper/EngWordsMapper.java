package com.chason.mapper;

import com.chason.pojo.EngWords;

import java.util.List;

public interface EngWordsMapper {

    int insertEnglishWord(EngWords engWords);

    List<EngWords> selectAll();

}
