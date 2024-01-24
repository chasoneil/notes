package com.chason.enums;

public enum WordType {

    NOUN("名词"),

    VERB("动词"),

    ADJECTIVE("形容词"),

    ADVERB("副词"),

    PRONOUN("代词"),

    MIMETIC("拟声词");

    private final String type;

    private WordType (String type) {
        this.type = type;
    }

}
