package com.chason.entity.english;

import lombok.Data;

@Data
public class EngSentence {

    private int id;

    private String engWord;

    private String engSentence;

    private String cnMean;

    private String fileIndex;

    public EngSentence (String engWord) {
        this.engWord = engWord;
    }

    public EngSentence (String engWord, String index) {
        this.engWord = engWord;
        this.fileIndex = index;
    }

    public EngSentence() {}

}
