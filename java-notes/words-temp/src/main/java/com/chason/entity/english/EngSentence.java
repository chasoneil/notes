package com.chason.entity.english;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;


@Data
public class EngSentence {

    private int id;

    private String engWord;

    private String engSentence;

    private String fileIndex;

    public EngSentence (String engWord) {
        this.engWord = engWord;
    }

    public EngSentence (String engWord, String index) {
        this.engWord = engWord;
        this.fileIndex = index;
    }

    public EngSentence() {}

    public List<Sentence> sentences () {
        return JSON.parseArray(this.engSentence, Sentence.class);
    }

}
