package com.chason.mapper;

import com.chason.entity.english.EngSentence;

import java.util.List;

public interface EngSentenceMapper {

    int insert(EngSentence engSentence);

    List<EngSentence> listAll();

    List<EngSentence> listByIndex(String index);
}
