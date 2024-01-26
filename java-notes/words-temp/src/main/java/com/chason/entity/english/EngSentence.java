package com.chason.entity.english;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;


@Data
public class EngSentence {

    private int id;

    private String engWord;

    private String engSentence;

    public EngSentence (String engWord, String engSentence) {
        this.engWord = engWord;
        this.engSentence = engSentence;
    }

    public EngSentence (String engWord) {
        this.engWord = engWord;
    }

    public EngSentence() {}

    public List<Sentence> sentences () {
        return JSON.parseArray(this.engSentence, Sentence.class);
    }

}
