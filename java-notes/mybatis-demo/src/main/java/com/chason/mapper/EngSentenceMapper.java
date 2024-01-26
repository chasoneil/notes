package com.chason.mapper;


import com.chason.pojo.EngSentence;

import java.util.List;

public interface EngSentenceMapper {

    int save(EngSentence engSentence);

    List<EngSentence> listAll();
}
