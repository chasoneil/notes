package com.chason.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class EngSentence {

    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String engWord;

    @Setter
    @Getter
    private String engSentence;

    public EngSentence (String engWord, String engSentence) {
        this.engWord = engWord;
        this.engSentence = engSentence;
    }

    public EngSentence() {}

    public List<Sentence> sentences () {
        return JSON.parseArray(this.engSentence, Sentence.class);
    }

}
