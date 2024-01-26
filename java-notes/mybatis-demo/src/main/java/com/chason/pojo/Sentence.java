package com.chason.pojo;

import lombok.Data;

@Data
public class Sentence {

    private String sentence;

    private String mean;

    public Sentence(String sentence, String mean) {
        this.sentence = sentence;
        this.mean = mean;
    }

    public Sentence() {}

}
